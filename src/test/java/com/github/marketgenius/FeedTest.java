package com.github.marketgenius;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FeedTest {

	@Test
	public void testToString() {
		
		assertThat(FeedBuilder.create().feedCode("feed1").market("market1").feedId(1).build()).isEqualTo("{feedcode:'feed1', market:'market1', feedId:1}");
	}

}
