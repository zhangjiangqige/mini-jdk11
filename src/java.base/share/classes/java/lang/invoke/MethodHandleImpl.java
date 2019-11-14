package java.lang.invoke;

import org.checkerframework.checker.interning.qual.UsesObjectEquals;
import org.checkerframework.framework.qual.AnnotatedFor;
import jdk.internal.misc.JavaLangInvokeAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.reflect.CallerSensitive;
import jdk.internal.reflect.Reflection;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Stable;
import sun.invoke.empty.Empty;
import sun.invoke.util.ValueConversions;
import sun.invoke.util.VerifyType;
import sun.invoke.util.Wrapper;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import static java.lang.invoke.LambdaForm.*;
import static java.lang.invoke.MethodHandleStatics.*;
import static java.lang.invoke.MethodHandles.Lookup.IMPL_LOOKUP;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

@AnnotatedFor({ "interning" })
@UsesObjectEquals
abstract class MethodHandleImpl {

    static MethodHandle makeArrayElementAccessor(Class<?> arrayClass, ArrayAccess access);

    enum ArrayAccess {

        GET, SET, LENGTH;

        static String opName(ArrayAccess a) {
            switch(a) {
                case GET:
                    return "getElement";
                case SET:
                    return "setElement";
                case LENGTH:
                    return "length";
            }
            throw unmatchedArrayAccess(a);
        }

        static MethodHandle objectAccessor(ArrayAccess a) {
            switch(a) {
                case GET:
                    return ArrayAccessor.OBJECT_ARRAY_GETTER;
                case SET:
                    return ArrayAccessor.OBJECT_ARRAY_SETTER;
                case LENGTH:
                    return ArrayAccessor.OBJECT_ARRAY_LENGTH;
            }
            throw unmatchedArrayAccess(a);
        }

        static int cacheIndex(ArrayAccess a) {
            switch(a) {
                case GET:
                    return ArrayAccessor.GETTER_INDEX;
                case SET:
                    return ArrayAccessor.SETTER_INDEX;
                case LENGTH:
                    return ArrayAccessor.LENGTH_INDEX;
            }
            throw unmatchedArrayAccess(a);
        }

        static Intrinsic intrinsic(ArrayAccess a) {
            switch(a) {
                case GET:
                    return Intrinsic.ARRAY_LOAD;
                case SET:
                    return Intrinsic.ARRAY_STORE;
                case LENGTH:
                    return Intrinsic.ARRAY_LENGTH;
            }
            throw unmatchedArrayAccess(a);
        }
    }

    static InternalError unmatchedArrayAccess(ArrayAccess a);

    static final class ArrayAccessor {

        static final int GETTER_INDEX = 0, SETTER_INDEX = 1, LENGTH_INDEX = 2, INDEX_LIMIT = 3;

        static final ClassValue<MethodHandle[]> TYPED_ACCESSORS = new ClassValue<MethodHandle[]>() {

            @Override
            protected MethodHandle[] computeValue(Class<?> type) {
                return new MethodHandle[INDEX_LIMIT];
            }
        };

        static final MethodHandle OBJECT_ARRAY_GETTER, OBJECT_ARRAY_SETTER, OBJECT_ARRAY_LENGTH;

        static {
            MethodHandle[] cache = TYPED_ACCESSORS.get(Object[].class);
            cache[GETTER_INDEX] = OBJECT_ARRAY_GETTER = makeIntrinsic(getAccessor(Object[].class, ArrayAccess.GET), Intrinsic.ARRAY_LOAD);
            cache[SETTER_INDEX] = OBJECT_ARRAY_SETTER = makeIntrinsic(getAccessor(Object[].class, ArrayAccess.SET), Intrinsic.ARRAY_STORE);
            cache[LENGTH_INDEX] = OBJECT_ARRAY_LENGTH = makeIntrinsic(getAccessor(Object[].class, ArrayAccess.LENGTH), Intrinsic.ARRAY_LENGTH);
            assert (InvokerBytecodeGenerator.isStaticallyInvocable(ArrayAccessor.OBJECT_ARRAY_GETTER.internalMemberName()));
            assert (InvokerBytecodeGenerator.isStaticallyInvocable(ArrayAccessor.OBJECT_ARRAY_SETTER.internalMemberName()));
            assert (InvokerBytecodeGenerator.isStaticallyInvocable(ArrayAccessor.OBJECT_ARRAY_LENGTH.internalMemberName()));
        }

        static int getElementI(int[] a, int i);

        static long getElementJ(long[] a, int i);

        static float getElementF(float[] a, int i);

        static double getElementD(double[] a, int i);

        static boolean getElementZ(boolean[] a, int i);

        static byte getElementB(byte[] a, int i);

        static short getElementS(short[] a, int i);

        static char getElementC(char[] a, int i);

        static Object getElementL(Object[] a, int i);

        static void setElementI(int[] a, int i, int x);

        static void setElementJ(long[] a, int i, long x);

        static void setElementF(float[] a, int i, float x);

        static void setElementD(double[] a, int i, double x);

        static void setElementZ(boolean[] a, int i, boolean x);

        static void setElementB(byte[] a, int i, byte x);

        static void setElementS(short[] a, int i, short x);

        static void setElementC(char[] a, int i, char x);

        static void setElementL(Object[] a, int i, Object x);

        static int lengthI(int[] a);

        static int lengthJ(long[] a);

        static int lengthF(float[] a);

        static int lengthD(double[] a);

        static int lengthZ(boolean[] a);

        static int lengthB(byte[] a);

        static int lengthS(short[] a);

        static int lengthC(char[] a);

        static int lengthL(Object[] a);

        static String name(Class<?> arrayClass, ArrayAccess access);

        static MethodType type(Class<?> arrayClass, ArrayAccess access);

        static MethodType correctType(Class<?> arrayClass, ArrayAccess access);

        static MethodHandle getAccessor(Class<?> arrayClass, ArrayAccess access);
    }

    static MethodHandle makePairwiseConvert(MethodHandle target, MethodType srcType, boolean strict, boolean monobox);

    private static int countNonNull(Object[] array);

    static MethodHandle makePairwiseConvertByEditor(MethodHandle target, MethodType srcType, boolean strict, boolean monobox);

    static MethodHandle makePairwiseConvertIndirect(MethodHandle target, MethodType srcType, boolean strict, boolean monobox);

    static Object[] computeValueConversions(MethodType srcType, MethodType dstType, boolean strict, boolean monobox);

    static MethodHandle makePairwiseConvert(MethodHandle target, MethodType srcType, boolean strict);

    static Object valueConversion(Class<?> src, Class<?> dst, boolean strict, boolean monobox);

    static MethodHandle makeVarargsCollector(MethodHandle target, Class<?> arrayType);

    private static final class AsVarargsCollector extends DelegatingMethodHandle {

        private final MethodHandle target;

        private final Class<?> arrayType;

        @Stable
        private MethodHandle asCollectorCache;

        AsVarargsCollector(MethodHandle target, Class<?> arrayType) {
            this(target.type(), target, arrayType);
        }

        AsVarargsCollector(MethodType type, MethodHandle target, Class<?> arrayType) {
            super(type, target);
            this.target = target;
            this.arrayType = arrayType;
        }

        @Override
        public boolean isVarargsCollector();

        @Override
        protected MethodHandle getTarget();

        @Override
        public MethodHandle asFixedArity();

        @Override
        MethodHandle setVarargs(MemberName member);

        @Override
        public MethodHandle withVarargs(boolean makeVarargs);

        @Override
        public MethodHandle asTypeUncached(MethodType newType);

        @Override
        boolean viewAsTypeChecks(MethodType newType, boolean strict);

        @Override
        public Object invokeWithArguments(Object... arguments) throws Throwable;
    }

    static MethodHandle makeSpreadArguments(MethodHandle target, Class<?> spreadArgType, int spreadArgPos, int spreadArgCount);

    static void checkSpreadArgument(Object av, int n);

    static MethodHandle makeCollectArguments(MethodHandle target, MethodHandle collector, int collectArgPos, boolean retainOriginalArgs);

    @LambdaForm.Hidden
    static MethodHandle selectAlternative(boolean testResult, MethodHandle target, MethodHandle fallback);

    @LambdaForm.Hidden
    @jdk.internal.HotSpotIntrinsicCandidate
    static boolean profileBoolean(boolean result, int[] counters);

    @LambdaForm.Hidden
    @jdk.internal.HotSpotIntrinsicCandidate
    static boolean isCompileConstant(Object obj);

    static MethodHandle makeGuardWithTest(MethodHandle test, MethodHandle target, MethodHandle fallback);

    static MethodHandle profile(MethodHandle target);

    static MethodHandle makeBlockInliningWrapper(MethodHandle target);

    private final static class Makers {

        static final Function<MethodHandle, LambdaForm> PRODUCE_BLOCK_INLINING_FORM = new Function<MethodHandle, LambdaForm>() {

            @Override
            public LambdaForm apply(MethodHandle target) {
                return DelegatingMethodHandle.makeReinvokerForm(target, MethodTypeForm.LF_DELEGATE_BLOCK_INLINING, CountingWrapper.class, false, DelegatingMethodHandle.NF_getTarget, CountingWrapper.NF_maybeStopCounting);
            }
        };

        static final Function<MethodHandle, LambdaForm> PRODUCE_REINVOKER_FORM = new Function<MethodHandle, LambdaForm>() {

            @Override
            public LambdaForm apply(MethodHandle target) {
                return DelegatingMethodHandle.makeReinvokerForm(target, MethodTypeForm.LF_DELEGATE, DelegatingMethodHandle.class, DelegatingMethodHandle.NF_getTarget);
            }
        };

        static final ClassValue<MethodHandle[]> TYPED_COLLECTORS = new ClassValue<MethodHandle[]>() {

            @Override
            protected MethodHandle[] computeValue(Class<?> type) {
                return new MethodHandle[MAX_JVM_ARITY + 1];
            }
        };
    }

    static class CountingWrapper extends DelegatingMethodHandle {

        private final MethodHandle target;

        private int count;

        private Function<MethodHandle, LambdaForm> countingFormProducer;

        private Function<MethodHandle, LambdaForm> nonCountingFormProducer;

        private volatile boolean isCounting;

        private CountingWrapper(MethodHandle target, LambdaForm lform, Function<MethodHandle, LambdaForm> countingFromProducer, Function<MethodHandle, LambdaForm> nonCountingFormProducer, int count) {
            super(target.type(), lform);
            this.target = target;
            this.count = count;
            this.countingFormProducer = countingFromProducer;
            this.nonCountingFormProducer = nonCountingFormProducer;
            this.isCounting = (count > 0);
        }

        @Hidden
        @Override
        protected MethodHandle getTarget();

        @Override
        public MethodHandle asTypeUncached(MethodType newType);

        private int invocations = CUSTOMIZE_THRESHOLD;

        private void maybeCustomizeTarget();

        boolean countDown();

        @Hidden
        static void maybeStopCounting(Object o1);

        static final NamedFunction NF_maybeStopCounting;

        static {
            Class<?> THIS_CLASS = CountingWrapper.class;
            try {
                NF_maybeStopCounting = new NamedFunction(THIS_CLASS.getDeclaredMethod("maybeStopCounting", Object.class));
            } catch (ReflectiveOperationException ex) {
                throw newInternalError(ex);
            }
        }
    }

    static LambdaForm makeGuardWithTestForm(MethodType basicType);

    private static LambdaForm makeGuardWithCatchForm(MethodType basicType);

    static MethodHandle makeGuardWithCatch(MethodHandle target, Class<? extends Throwable> exType, MethodHandle catcher);

    @LambdaForm.Hidden
    static Object guardWithCatch(MethodHandle target, Class<? extends Throwable> exType, MethodHandle catcher, Object... av) throws Throwable;

    @LambdaForm.Hidden
    private static Object[] prepend(Object[] array, Object... elems);

    static MethodHandle throwException(MethodType type);

    static <T extends Throwable> Empty throwException(T t) throws T;

    static MethodHandle[] FAKE_METHOD_HANDLE_INVOKE = new MethodHandle[2];

    static MethodHandle fakeMethodHandleInvoke(MemberName method);

    static MethodHandle fakeVarHandleInvoke(MemberName method);

    static MethodHandle bindCaller(MethodHandle mh, Class<?> hostClass);

    private static class BindCaller {

        private static MethodType INVOKER_MT = MethodType.methodType(Object.class, MethodHandle.class, Object[].class);

        static MethodHandle bindCaller(MethodHandle mh, Class<?> hostClass);

        private static MethodHandle makeInjectedInvoker(Class<?> hostClass);

        private static ClassValue<MethodHandle> CV_makeInjectedInvoker = new ClassValue<MethodHandle>() {

            @Override
            protected MethodHandle computeValue(Class<?> hostClass) {
                return makeInjectedInvoker(hostClass);
            }
        };

        private static MethodHandle prepareForInvoker(MethodHandle mh);

        private static MethodHandle restoreToType(MethodHandle vamh, MethodHandle original, Class<?> hostClass);

        private static boolean checkInjectedInvoker(Class<?> hostClass, Class<?> invokerClass);

        private static final MethodHandle MH_checkCallerClass;

        static {
            final Class<?> THIS_CLASS = BindCaller.class;
            assert (checkCallerClass(THIS_CLASS));
            try {
                MH_checkCallerClass = IMPL_LOOKUP.findStatic(THIS_CLASS, "checkCallerClass", MethodType.methodType(boolean.class, Class.class));
                assert ((boolean) MH_checkCallerClass.invokeExact(THIS_CLASS));
            } catch (Throwable ex) {
                throw new InternalError(ex);
            }
        }

        @CallerSensitive
        @ForceInline
        private static boolean checkCallerClass(Class<?> expected);

        private static final byte[] INJECTED_INVOKER_TEMPLATE = generateInvokerTemplate();

        private static byte[] generateInvokerTemplate();
    }

    private static final class WrappedMember extends DelegatingMethodHandle {

        private final MethodHandle target;

        private final MemberName member;

        private final Class<?> callerClass;

        private final boolean isInvokeSpecial;

        private WrappedMember(MethodHandle target, MethodType type, MemberName member, boolean isInvokeSpecial, Class<?> callerClass) {
            super(type, target);
            this.target = target;
            this.member = member;
            this.callerClass = callerClass;
            this.isInvokeSpecial = isInvokeSpecial;
        }

        @Override
        MemberName internalMemberName();

        @Override
        Class<?> internalCallerClass();

        @Override
        boolean isInvokeSpecial();

        @Override
        protected MethodHandle getTarget();

        @Override
        public MethodHandle asTypeUncached(MethodType newType);
    }

    static MethodHandle makeWrappedMember(MethodHandle target, MemberName member, boolean isInvokeSpecial);

    enum Intrinsic {

        SELECT_ALTERNATIVE,
        GUARD_WITH_CATCH,
        TRY_FINALLY,
        LOOP,
        NEW_ARRAY,
        ARRAY_LOAD,
        ARRAY_STORE,
        ARRAY_LENGTH,
        IDENTITY,
        ZERO,
        NONE
    }

    static final class IntrinsicMethodHandle extends DelegatingMethodHandle {

        private final MethodHandle target;

        private final Intrinsic intrinsicName;

        IntrinsicMethodHandle(MethodHandle target, Intrinsic intrinsicName) {
            super(target.type(), target);
            this.target = target;
            this.intrinsicName = intrinsicName;
        }

        @Override
        protected MethodHandle getTarget();

        @Override
        Intrinsic intrinsicName();

        @Override
        public MethodHandle asTypeUncached(MethodType newType);

        @Override
        String internalProperties();

        @Override
        public MethodHandle asCollector(Class<?> arrayType, int arrayLength);
    }

    static MethodHandle makeIntrinsic(MethodHandle target, Intrinsic intrinsicName);

    static MethodHandle makeIntrinsic(MethodType type, LambdaForm form, Intrinsic intrinsicName);

    private static MethodHandle findCollector(String name, int nargs, Class<?> rtype, Class<?>... ptypes);

    private static final Object[] NO_ARGS_ARRAY = {};

    private static Object[] makeArray(Object... args);

    private static Object[] array();

    private static Object[] array(Object a0);

    private static Object[] array(Object a0, Object a1);

    private static Object[] array(Object a0, Object a1, Object a2);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4, Object a5);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8);

    private static Object[] array(Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9);

    private static final int ARRAYS_COUNT = 11;

    @Stable
    private static final MethodHandle[] ARRAYS = new MethodHandle[MAX_ARITY + 1];

    private static Object[] fillNewArray(Integer len, Object[] args);

    private static Object[] fillNewTypedArray(Object[] example, Integer len, Object[] args);

    private static void fillWithArguments(Object[] a, int pos, Object... args);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4, Object a5);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8);

    private static Object[] fillArray(Integer pos, Object[] a, Object a0, Object a1, Object a2, Object a3, Object a4, Object a5, Object a6, Object a7, Object a8, Object a9);

    private static final int FILL_ARRAYS_COUNT = 11;

    @Stable
    private static final MethodHandle[] FILL_ARRAYS = new MethodHandle[FILL_ARRAYS_COUNT];

    private static MethodHandle getFillArray(int count);

    private static Object copyAsPrimitiveArray(Wrapper w, Object... boxes);

    static MethodHandle varargsArray(int nargs);

    private static boolean assertCorrectArity(MethodHandle mh, int arity);

    static <T> T[] identity(T[] x);

    private static MethodHandle buildVarargsArray(MethodHandle newArray, MethodHandle finisher, int nargs);

    private static final int LEFT_ARGS = FILL_ARRAYS_COUNT - 1;

    @Stable
    private static final MethodHandle[] FILL_ARRAY_TO_RIGHT = new MethodHandle[MAX_ARITY + 1];

    private static MethodHandle fillToRight(int nargs);

    private static MethodHandle buildFiller(int nargs);

    static final int MAX_JVM_ARITY = 255;

    static MethodHandle varargsArray(Class<?> arrayType, int nargs);

    private static MethodHandle buildArrayProducer(Class<?> arrayType);

    static void assertSame(Object mh1, Object mh2);

    static final byte NF_checkSpreadArgument = 0, NF_guardWithCatch = 1, NF_throwException = 2, NF_tryFinally = 3, NF_loop = 4, NF_profileBoolean = 5, NF_LIMIT = 6;

    @Stable
    private static final NamedFunction[] NFS = new NamedFunction[NF_LIMIT];

    static NamedFunction getFunction(byte func);

    private static NamedFunction createFunction(byte func);

    static {
        SharedSecrets.setJavaLangInvokeAccess(new JavaLangInvokeAccess() {

            @Override
            public Object newMemberName() {
                return new MemberName();
            }

            @Override
            public String getName(Object mname) {
                MemberName memberName = (MemberName) mname;
                return memberName.getName();
            }

            @Override
            public Class<?> getDeclaringClass(Object mname) {
                MemberName memberName = (MemberName) mname;
                return memberName.getDeclaringClass();
            }

            @Override
            public MethodType getMethodType(Object mname) {
                MemberName memberName = (MemberName) mname;
                return memberName.getMethodType();
            }

            @Override
            public String getMethodDescriptor(Object mname) {
                MemberName memberName = (MemberName) mname;
                return memberName.getMethodDescriptor();
            }

            @Override
            public boolean isNative(Object mname) {
                MemberName memberName = (MemberName) mname;
                return memberName.isNative();
            }

            @Override
            public byte[] generateDirectMethodHandleHolderClassBytes(String className, MethodType[] methodTypes, int[] types) {
                return GenerateJLIClassesHelper.generateDirectMethodHandleHolderClassBytes(className, methodTypes, types);
            }

            @Override
            public byte[] generateDelegatingMethodHandleHolderClassBytes(String className, MethodType[] methodTypes) {
                return GenerateJLIClassesHelper.generateDelegatingMethodHandleHolderClassBytes(className, methodTypes);
            }

            @Override
            public Map.Entry<String, byte[]> generateConcreteBMHClassBytes(final String types) {
                return GenerateJLIClassesHelper.generateConcreteBMHClassBytes(types);
            }

            @Override
            public byte[] generateBasicFormsClassBytes(final String className) {
                return GenerateJLIClassesHelper.generateBasicFormsClassBytes(className);
            }

            @Override
            public byte[] generateInvokersHolderClassBytes(final String className, MethodType[] invokerMethodTypes, MethodType[] callSiteMethodTypes) {
                return GenerateJLIClassesHelper.generateInvokersHolderClassBytes(className, invokerMethodTypes, callSiteMethodTypes);
            }
        });
    }

    private static MethodHandle unboxResultHandle(Class<?> returnType);

    static MethodHandle makeLoop(Class<?> tloop, List<Class<?>> targs, List<MethodHandle> init, List<MethodHandle> step, List<MethodHandle> pred, List<MethodHandle> fini);

    private static MethodHandle[] toArray(List<MethodHandle> l);

    private static LambdaForm makeLoopForm(MethodType basicType, BasicType[] localVarTypes);

    static class LoopClauses {

        @Stable
        final MethodHandle[][] clauses;

        LoopClauses(MethodHandle[][] clauses) {
            assert clauses.length == 4;
            this.clauses = clauses;
        }

        @Override
        public String toString();
    }

    @LambdaForm.Hidden
    static Object loop(BasicType[] localTypes, LoopClauses clauseData, Object... av) throws Throwable;

    static boolean countedLoopPredicate(int limit, int counter);

    static int countedLoopStep(int limit, int counter);

    static Iterator<?> initIterator(Iterable<?> it);

    static boolean iteratePredicate(Iterator<?> it);

    static Object iterateNext(Iterator<?> it);

    static MethodHandle makeTryFinally(MethodHandle target, MethodHandle cleanup, Class<?> rtype, List<Class<?>> argTypes);

    private static LambdaForm makeTryFinallyForm(MethodType basicType);

    @LambdaForm.Hidden
    static Object tryFinally(MethodHandle target, MethodHandle cleanup, Object... av) throws Throwable;

    static final int MH_cast = 0, MH_selectAlternative = 1, MH_copyAsPrimitiveArray = 2, MH_fillNewTypedArray = 3, MH_fillNewArray = 4, MH_arrayIdentity = 5, MH_countedLoopPred = 6, MH_countedLoopStep = 7, MH_initIterator = 8, MH_iteratePred = 9, MH_iterateNext = 10, MH_Array_newInstance = 11, MH_LIMIT = 12;

    static MethodHandle getConstantHandle(int idx);

    private static synchronized MethodHandle setCachedHandle(int idx, final MethodHandle method);

    @Stable
    private static final MethodHandle[] HANDLES = new MethodHandle[MH_LIMIT];

    private static MethodHandle makeConstantHandle(int idx);
}
