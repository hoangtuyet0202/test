package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateMediaSupportPlaceTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@//suppose that 10 11 12 13 14 support place rush order
	@ParameterizedTest
	@CsvSource({
			"10,true",
			"11,true",
			"20,false"
	})


	void test(int id, boolean expected) {
		boolean isSupport = placeRushOrderController.checkMediaSupport(id);
		System.out.println(id);
		assertEquals(expected, isSupport);
	}

}