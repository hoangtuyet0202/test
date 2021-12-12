package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
class ValidateLocationSupportTest {

	private PlaceRushOrderController placeRushOrderController;

	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController = new PlaceRushOrderController();
	}

	@ParameterizedTest
	@CsvSource({
			"Hà Nội,true",
			"Phú Thọ,false",
			"Hồ Chí Minh,true"
	})

	void test(String province, boolean expected) {
		boolean isValid = placeRushOrderController.checkLocationSupport(province);
		System.out.println(province);
		assertEquals(expected, isValid);

	}

}