package com.wjh.generationcode.GeneratorConfiguration;

import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.internal.ObjectFactory;

import java.util.List;

public class InsoIntrospectedTable extends IntrospectedTableMyBatis3SimpleImpl {

    /**
     * XML的生成方法
     * @param javaClientGenerator
     * @param warnings
     * @param progressCallback
     */
    @Override
    protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator, List<String> warnings, ProgressCallback progressCallback) {
        this.xmlMapperGenerator = new MySimpleXMLMapperGenerator();
        this.initializeAbstractGenerator(this.xmlMapperGenerator, warnings, progressCallback);
    }

    /**
     * Mapper类的生成方法
     * @return
     */
    @Override
    protected AbstractJavaClientGenerator createJavaClientGenerator() {
        if (this.context.getJavaClientGeneratorConfiguration() == null) {
            return null;
        } else {
            String type = this.context.getJavaClientGeneratorConfiguration().getConfigurationType();
            Object javaGenerator;
            if ("XMLMAPPER".equalsIgnoreCase(type)) {
                javaGenerator = new MySimpleJavaClientGenerator();
            } else if ("ANNOTATEDMAPPER".equalsIgnoreCase(type)) {
                javaGenerator = new MySimpleJavaClientGenerator();
            } else if ("MAPPER".equalsIgnoreCase(type)) {
                javaGenerator = new MySimpleJavaClientGenerator();
            } else {
                javaGenerator = (AbstractJavaClientGenerator) ObjectFactory.createInternalObject(type);
            }
            return (AbstractJavaClientGenerator)javaGenerator;

        }
    }

    /**
     * model类的生成方法
     * @param warnings
     * @param progressCallback
     */
    @Override
    protected void calculateJavaModelGenerators(List<String> warnings, ProgressCallback progressCallback) {
        super.calculateJavaModelGenerators(warnings, progressCallback);
    }
}
