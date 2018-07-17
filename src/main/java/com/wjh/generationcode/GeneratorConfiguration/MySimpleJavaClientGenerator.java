package com.wjh.generationcode.GeneratorConfiguration;

import com.mysql.jdbc.Messages;
import com.wjh.generationcode.bean.FileUrl;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.SimpleJavaClientGenerator;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.*;

public class MySimpleJavaClientGenerator extends SimpleJavaClientGenerator {

    static FileUrl fileUrl;

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        this.progressCallback.startTask(Messages.getString("Progress.17", new String[]{this.introspectedTable.getFullyQualifiedTable().toString()}));
        CommentGenerator commentGenerator = this.context.getCommentGenerator();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaMapperType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        //添加添加类注释，这个是添加到类头部，没有多大作用
        commentGenerator.addJavaFileComment(interfaze);
        String rootInterface = this.introspectedTable.getTableConfigurationProperty("rootInterface");
        if (!StringUtility.stringHasValue(rootInterface)) {
            rootInterface = this.context.getJavaClientGeneratorConfiguration().getProperty("rootInterface");
        }
        if (StringUtility.stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }


        this.addDeleteByPrimaryKeyMethod(interfaze);
        this.addInsertMethod(interfaze);
        this.addSelectByPrimaryKeyMethod(interfaze);
        this.addSelectAllMethod(interfaze);
        this.addUpdateByPrimaryKeyMethod(interfaze);
        //this.addCountListMethod(interfaze);
      //  this.addQueryPageListMethod(interfaze);
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (this.context.getPlugins().clientGenerated(interfaze, (TopLevelClass) null, this.introspectedTable)) {
            answer.add(interfaze);
        }

        List<CompilationUnit> extraCompilationUnits = this.getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }
        return answer;
    }
}
