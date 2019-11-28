package com.supcon.whd.compiler;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.supcon.whd.annotation.ApiFactory;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Filer;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import static com.squareup.javapoet.TypeSpec.classBuilder;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Created by baixiaokang on 16/12/28.
 */

public class ApiFactoryProcessor implements IProcessor {

    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor, Filer filer,Elements mElementsUtils) {

        try {
            for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(ApiFactory.class))) {
                String packageName =  mElementsUtils.getPackageOf(element).getQualifiedName().toString();
                ApiFactory apiFactory=element.getAnnotation(ApiFactory.class);

                String interfaceName=apiFactory.interfaceName();

                String CLASS_NAME = apiFactory.name();
                String DATA_ARR_CLASS = "DataArr";
                TypeSpec.Builder tb = classBuilder(CLASS_NAME).addModifiers(PUBLIC, FINAL).addJavadoc("@ factory created by apt");
                mAbstractProcessor.mMessager.printMessage(Diagnostic.Kind.NOTE, "正在处理: " + element.toString());
                for (Element e : element.getEnclosedElements()) {
                    ExecutableElement executableElement = (ExecutableElement) e;
                    MethodSpec.Builder methodBuilder =
                            MethodSpec.methodBuilder(e.getSimpleName().toString())
                                    .addJavadoc("@created by apt")
                                    .addModifiers(PUBLIC, STATIC);

                    if (TypeName.get(executableElement.getReturnType()).toString().contains(DATA_ARR_CLASS)) {//返回列表数据
                        methodBuilder.returns(ClassName.get("io.reactivex", "Flowable"));
                        Map<String, Object> params = new HashMap<>();
                        methodBuilder.addParameter(params.getClass(), "param");
                        ClassName apiUtil = ClassName.get("com.base.util", "ApiUtil");
                        ClassName C = ClassName.get("com", "C");
                        CodeBlock.Builder blockBuilder = CodeBlock.builder();
                        int len = executableElement.getParameters().size();
                        for (int i = 0; i < len; i++) {
                            VariableElement ep = executableElement.getParameters().get(i);
                            boolean isLast = i == len - 1;
                            String split = (isLast ? "" : ",");
                            switch (ep.getSimpleName().toString()) {
                                case "include":
                                    blockBuilder.add("$L.getInclude(param)" + split, apiUtil);
                                    break;
                                case "where":
                                    blockBuilder.add("$L.getWhere(param)" + split, apiUtil);
                                    break;
                                case "skip":
                                    blockBuilder.add("$L.getSkip(param)" + split, apiUtil);
                                    break;
                                case "limit":
                                    blockBuilder.add("$L.PAGE_COUNT" + split, C);
                                    break;
                                case "order":
                                    blockBuilder.add("$L._CREATED_AT" + split, C);
                                    break;
                            }
                        }
                        methodBuilder.addStatement(
                                "return $T.getInstance()" +
                                        ".service.$L($L)" +
                                        ".compose($T.io_main())"
                                , ClassName.get("com.supcon.whd.common.base.network", "Api")
                                , e.getSimpleName().toString()
                                , blockBuilder.build().toString()
                                , ClassName.get("com.supcon.whd.common.base.network", "RxSchedulers"));
                        tb.addMethod(methodBuilder.build());
                    } else {
                        methodBuilder.returns(TypeName.get(executableElement.getReturnType()));
                        String paramsString = "";
                        for (VariableElement ep : executableElement.getParameters()) {
                            methodBuilder.addParameter(TypeName.get(ep.asType()), ep.getSimpleName().toString());
                            paramsString += ep.getSimpleName().toString() + ",";
                        }
                        methodBuilder.addStatement(
                                "return $T.getInstance()" +
                                        ".retrofit.create($T.class).$L($L)" +
                                        ".compose($T.io_main())"
                                , ClassName.get("com.supcon.whd.common.base.network", "Api")
                                , ClassName.get(packageName,interfaceName)
                                , e.getSimpleName().toString()
                                , paramsString.substring(0, paramsString.length() - 1)
                                , ClassName.get("com.supcon.whd.common.base.network", "RxSchedulers"));
                        tb.addMethod(methodBuilder.build());
                    }
                }
                JavaFile javaFile = JavaFile.builder(packageName, tb.build()).build();
                javaFile.writeTo(mAbstractProcessor.mFiler);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
