package com.sun.javadoc;

import org.checkerframework.framework.qual.AnnotatedFor;

@AnnotatedFor({ "lock" })
@Deprecated(since = "9", forRemoval = true)
@SuppressWarnings("removal")
public interface ClassDoc extends ProgramElementDoc, Type {

    boolean isAbstract();

    boolean isSerializable();

    boolean isExternalizable();

    MethodDoc[] serializationMethods();

    FieldDoc[] serializableFields();

    boolean definesSerializableFields();

    ClassDoc superclass();

    Type superclassType();

    boolean subclassOf(ClassDoc cd);

    ClassDoc[] interfaces();

    Type[] interfaceTypes();

    TypeVariable[] typeParameters();

    ParamTag[] typeParamTags();

    FieldDoc[] fields();

    FieldDoc[] fields(boolean filter);

    FieldDoc[] enumConstants();

    MethodDoc[] methods();

    MethodDoc[] methods(boolean filter);

    ConstructorDoc[] constructors();

    ConstructorDoc[] constructors(boolean filter);

    ClassDoc[] innerClasses();

    ClassDoc[] innerClasses(boolean filter);

    ClassDoc findClass(String className);

    @Deprecated(since = "9", forRemoval = true)
    ClassDoc[] importedClasses();

    @Deprecated(since = "9", forRemoval = true)
    PackageDoc[] importedPackages();
}
