package com.shahuwang.bootexp.mainloader;

import org.springframework.asm.*;

import java.io.*;
import java.util.*;

public class Loader {
    private static final String MAIN_METHOD_NAME = "main";
    private static final String DOT_CLASS = ".class";
    private static final Type STRING_ARRAY_TYPE = Type.getType(String[].class);
    private static final Type MAIN_METHOD_TYPE = Type.getMethodType(Type.VOID_TYPE, STRING_ARRAY_TYPE);

    private static final FileFilter CLASS_FILE_FILTER = Loader::isClassFile;

    private static final FileFilter PACKAGE_FOLDER_FILTER = Loader::isPackageFolder;

    private static boolean isClassFile(File file) {
        return file.isFile() && file.getName().endsWith(DOT_CLASS);
    }

    private static boolean isPackageFolder(File file) {
        return file.isDirectory() && !file.getName().startsWith(".");
    }

    public static void main(String[] args) throws IOException {
        Loader ld = new Loader();
        String name = ld.load();
        System.out.println(name);
    }

    public String load() throws IOException {
        String SPRING_BOOT_APPLICATION_CLASS_NAME = "org.springframework.boot.autoconfigure.SpringBootApplication";
        File rootFolder = new File("C:\\share\\bootsample\\target\\classes");
        String prefix = rootFolder.getAbsolutePath() + "/";
        Deque<File> stack = new ArrayDeque<>();
        stack.push(rootFolder);
        while (!stack.isEmpty()) {
            File file = stack.pop();
            if (file.isFile()) {
                try (InputStream inputStream = new FileInputStream(file)) {
                    ClassDescriptor classDescriptor = createClassDescriptor(inputStream);
                    if (classDescriptor != null && classDescriptor.isMainMethodFound()) {
                        String className = convertToClassName(file.getAbsolutePath(), prefix);
                        if(annotated(SPRING_BOOT_APPLICATION_CLASS_NAME, classDescriptor.getAnnotationNames())){
                            return className;
                        }

                    }
                }
            }
            if (file.isDirectory()) {
                pushAllSorted(stack, file.listFiles(PACKAGE_FOLDER_FILTER));
                pushAllSorted(stack, file.listFiles(CLASS_FILE_FILTER));
            }
        }
        return "";
    }

    private boolean annotated(String targetName, Set<String> annotationNames){
        if(annotationNames.contains(targetName)){
            return true;
        }
        return false;
    }

    private static void pushAllSorted(Deque<File> stack, File[] files) {
        Arrays.sort(files, Comparator.comparing(File::getName));
        for (File file : files) {
            stack.push(file);
        }
    }

    private static String convertToClassName(String name, String prefix) {
        name = name.replace('/', '.');
        name = name.replace('\\', '.');
        name = name.substring(0, name.length() - DOT_CLASS.length());
        if (prefix != null) {
            name = name.substring(prefix.length());
        }
        return name;
    }

    private static ClassDescriptor createClassDescriptor(InputStream inputStream) {
        try {
            ClassReader classReader = new ClassReader(inputStream);
            ClassDescriptor classDescriptor = new ClassDescriptor();
            classReader.accept(classDescriptor, ClassReader.SKIP_CODE);
            return classDescriptor;
        }
        catch (IOException ex) {
            return null;
        }
    }


    private static class ClassDescriptor extends ClassVisitor {

        private final Set<String> annotationNames = new LinkedHashSet<>();

        private boolean mainMethodFound;

        ClassDescriptor() {
            super(SpringAsmInfo.ASM_VERSION);
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            this.annotationNames.add(Type.getType(desc).getClassName());
            return null;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (isAccess(access, Opcodes.ACC_PUBLIC, Opcodes.ACC_STATIC) && MAIN_METHOD_NAME.equals(name)
                    && MAIN_METHOD_TYPE.getDescriptor().equals(desc)) {
                this.mainMethodFound = true;
            }
            return null;
        }

        private boolean isAccess(int access, int... requiredOpsCodes) {
            for (int requiredOpsCode : requiredOpsCodes) {
                if ((access & requiredOpsCode) == 0) {
                    return false;
                }
            }
            return true;
        }

        boolean isMainMethodFound() {
            return this.mainMethodFound;
        }

        Set<String> getAnnotationNames() {
            return this.annotationNames;
        }

    }
}
