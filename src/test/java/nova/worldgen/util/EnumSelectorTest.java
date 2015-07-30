package nova.worldgen.util;

//TODO: someone fix this

//import org.assertj.core.api.Assertions;
//import org.junit.Test;
//import static nova.testutils.NovaAssertions.assertThat;


public class EnumSelectorTest {
	/*
	public enum TestEnum {
		ONE, TWO, THREE, FOUR
	}

	@Test(expected = IllegalStateException.class)
	public void testNotLocked() {
		EnumSelector<TestEnum> selector = EnumSelector.of(TestEnum.class);
		selector.allowAll();

		selector.allows(TestEnum.ONE);
	}

	@Test
	public void testLocked() {
		EnumSelector<TestEnum> selector = EnumSelector.of(TestEnum.class);
		selector.allowAll();

		selector.lock();

		Assertions.assertThat(selector.allows(TestEnum.ONE)).isTrue();
	}

	@Test
	public void testDefault() {
		EnumSelector<TestEnum> selector = EnumSelector.of(TestEnum.class);
		selector.allowAll();
		selector.lock();

		for(TestEnum entry : TestEnum.values()) {
			Assertions.assertThat(selector.allows(entry)).isTrue();
		}

		selector = EnumSelector.of(TestEnum.class);
		selector.blockAll();
		selector.lock();

		for(TestEnum entry : TestEnum.values()) {
			Assertions.assertThat(selector.allows(entry)).isFalse();
		}
	}

	@Test
	public void testBlacklist() {
		EnumSelector<TestEnum> selector = EnumSelector.of(TestEnum.class);
		selector.allowAll();
		selector.apart(TestEnum.TWO);
		selector.lock();

		Assertions.assertThat(selector.allows(TestEnum.ONE)).isTrue();
		Assertions.assertThat(selector.allows(TestEnum.TWO)).isFalse();
	}

	@Test
	public void testWhitelist() {
		EnumSelector<TestEnum> selector = EnumSelector.of(TestEnum.class);
		selector.blockAll();
		selector.apart(TestEnum.TWO);
		selector.lock();

		Assertions.assertThat(selector.allows(TestEnum.ONE)).isFalse();
		Assertions.assertThat(selector.allows(TestEnum.TWO)).isTrue();
	}*/
}
