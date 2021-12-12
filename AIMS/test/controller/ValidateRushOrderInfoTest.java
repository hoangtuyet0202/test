package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateRushOrderInfoTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"Nguyen Tien Vuong,true",
			"12345678_,false",
			"#1Vuong,false"
	})
	public void test(String info, boolean expected) {
		boolean isValided = placeRushOrderController.validateRushOrderInfo(info);
		assertEquals(true, isValided);
	}

}
