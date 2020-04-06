package com.supcon.whd.compiler;

import com.supcon.whd.annotation.ContractFactory;
import java.io.Writer;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

public class ContractProcessor implements IProcessor {
    private Filer mFilerUtils;
    private Types mTypesUtils;
    private Elements mElementsUtils;


//    @Override
//    public synchronized void init(ProcessingEnvironment processingEnv) {
//        super.init(processingEnv);
//
//        mFilerUtils = processingEnv.getFiler();
//        mTypesUtils = processingEnv.getTypeUtils();
//        mElementsUtils = processingEnv.getElementUtils();
//    }


//    @Override
//    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnv) {
//        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ContractFactory.class)) {
//            analysisAnnotated(annotatedElement);
//        }
//        return false;
//    }


    private void analysisAnnotated(Element typeElement) {
        String packageName = ((PackageElement) mElementsUtils.getPackageOf(typeElement)).getQualifiedName().toString();
        String apiName = typeElement.getSimpleName().toString();
        String helperName = "Contract" + apiName.substring(0, apiName.lastIndexOf("API"));
        packageName = packageName.substring(0, packageName.lastIndexOf(".")) + "";
        ContractFactory contractFactory = typeElement.getAnnotation(ContractFactory.class);

        StringBuffer builder = new StringBuffer();
        builder.append("package ").append(packageName).append(".contract").append(";\n");
        builder.append("import com.supcon.whd.common.contact.IBaseView;\n");
        builder.append("import com.supcon.whd.common.presenter.BasePresenter;\n");
        builder.append("import ").append(packageName).append(".api.").append(apiName).append(";").append("\n");
        String contract = contractFactory.toString();
        if (!contract.contains("entities"))
            return;

        String entitiesPackage = contract.substring(contract.indexOf("entities") + 9);
        entitiesPackage = entitiesPackage.replace(")", "");
        if (entitiesPackage.contains(",")) {
            String[] entities = entitiesPackage.split(",");
            for (String entity : entities) {
                builder.append("import ").append(entity).append(";").append("\n");
            }
        } else {
            builder.append("import ").append(entitiesPackage).append(";").append("\n");
        }
        builder.append("public interface ").append(helperName);
        builder.append("{\n\n");
        builder.append("\tinterface View extends IBaseView");
        builder.append("{\n\n");
        if (entitiesPackage.contains(",")) {
            String[] entities = entitiesPackage.split(",");
            for (String entity : entities) {
                String entityName=entity.substring(entity.lastIndexOf(".")+1);
                String name = entityName.substring(0, entityName.lastIndexOf("Entity"));
                String methodSuccessName = "do" + name + "Success";
                String methodSuccessVariable = toLowerCaseFirstOne(entityName);
                String methodFaliedName = "do" + name + "Failed";

                builder.append("\t\tvoid ").append(methodSuccessName).append("(").append(entityName).append(" ").append(methodSuccessVariable).append(");\n\n");
                builder.append("\t\tvoid ").append(methodFaliedName).append("(").append("String errMsg);\n\n");
            }
        } else {
            String entityName=entitiesPackage.substring(entitiesPackage.lastIndexOf(".")+1);
            String name = entityName.substring(0, entityName.lastIndexOf("Entity"));
            String methodSuccessName = "do" + name + "Success";
            String methodSuccessVariable = toLowerCaseFirstOne(entityName);
            String methodFaliedName = "do" + name + "Failed";

            builder.append("\t\tvoid ").append(methodSuccessName).append("(").append(entityName).append(" ").append(methodSuccessVariable).append(");\n\n");
            builder.append("\t\tvoid ").append(methodFaliedName).append("(").append("String errMsg);\n\n");
        }

        builder.append("\t}\n\n");
        builder.append("\tabstract class Presenter extends BasePresenter<").append(helperName).append(".").append("View> implements ").append(apiName);
        builder.append("{\n");
        builder.append("\t}\n");
        builder.append("}\n");
        try { // write the file
            JavaFileObject source = mFilerUtils.createSourceFile(packageName + ".contract." + helperName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }


    @Override
    public void process(RoundEnvironment roundEnv, AnnotationProcessor mAbstractProcessor, Filer filer, Elements elements) {
        mFilerUtils = filer;
        mElementsUtils = elements;
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(ContractFactory.class)) {
            analysisAnnotated(annotatedElement);
        }
    }
}
