package com.enjoy.compiler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * activity: A extends Activity
 */
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"com.enjoy.annotation.MyClass"})
public class TestProcessor extends AbstractProcessor {
    /**
     * javac调用此方法
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        roundEnvironment.processingOver()
//        set.isEmpty()

        // 写什么代码就做什么事情
        //process: javac编译时的一个回调
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "============>");

        String code = "\n" +
                "    public class A{\n" +
                "        \n" +
                "    }";
        if (!set.isEmpty()){
            Filer filer = processingEnv.getFiler();
            OutputStream outputStream = null;
            try {
                JavaFileObject sourceFile = filer.createSourceFile("A");
                outputStream = sourceFile.openOutputStream();
                outputStream.write(code.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 允许此注解处理器处理的注解
     * @return
     */
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
//    }
}
