package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.instrumentation.method.MethodDescription;
import net.bytebuddy.instrumentation.method.bytecode.stack.StackManipulation;
import net.bytebuddy.test.utility.MockitoRule;
import net.bytebuddy.test.utility.ObjectPropertyAssertion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MethodRebaseResolverDisabledTest {

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private MethodDescription methodDescription;

    @Test
    public void testResolutionPreservesMethod() throws Exception {
        MethodRebaseResolver.Resolution resolution = MethodRebaseResolver.Disabled.INSTANCE.resolve(methodDescription);
        assertThat(resolution.isRebased(), is(false));
        assertThat(resolution.getAdditionalArguments(), is((StackManipulation) StackManipulation.LegalTrivial.INSTANCE));
        assertThat(resolution.getResolvedMethod(), is(methodDescription));
    }

    @Test
    public void testNoAuxiliaryTypes() throws Exception {
        assertThat(MethodRebaseResolver.Disabled.INSTANCE.getAuxiliaryTypes().size(), is(0));
    }

    @Test
    public void testObjectProperties() throws Exception {
        ObjectPropertyAssertion.of(MethodRebaseResolver.Disabled.class).apply();
    }
}