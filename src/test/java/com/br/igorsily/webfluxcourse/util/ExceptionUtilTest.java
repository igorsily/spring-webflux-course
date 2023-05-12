package com.br.igorsily.webfluxcourse.util;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionUtilTest {
    private final Faker faker = new Faker(new Locale("pt", "BR"));

    @Test
    @DisplayName("Should return error message duplicate key")
    void getErrorDuplicateMessage() {


        String expected = "E11000 duplicate key error collection";

        String message = faker.lorem().sentence();

        String actual = ExceptionUtil.getErrorDuplicateMessage(message);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("Should return error email message duplicate key")
    void getErrorEmailDuplicateMessage() {

        String expected = "E-mail duplicate key";

        String message = faker.lorem().sentence().replace(" ", " email_index dup key ");

        String actual = ExceptionUtil.getErrorDuplicateMessage(message);

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("Should return IllegalAccessException when try to create a new instance of ExceptionUtil")
    void shouldReturnIllegalAccessException() throws NoSuchMethodException {

        Constructor<ExceptionUtil> constructor = ExceptionUtil.class.getDeclaredConstructor();

        assertThrows(IllegalAccessException.class, constructor::newInstance);


    }

}