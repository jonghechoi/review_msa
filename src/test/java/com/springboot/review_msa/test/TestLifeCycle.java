package com.springboot.review_msa.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
public class TestLifeCycle {

    @BeforeAll
    static void beforeAll() {
        log.debug("[beforeAll Annotation 호출] \n");
    }

    @AfterAll
    static void afterAll() {
        log.debug("[afterAll Annotation 호출] \n");
    }

    @BeforeEach
    void beforeEach() {
        log.debug("[beforeEach Annotation 호출] \n");
    }

    @AfterEach
    void afterEach() {
        log.debug("[afterEach Annotation 호출] \n");
    }

    @Test
    void test1() {
        log.debug("[test1 시작]");
    }

    @Test
    @DisplayName("TEST CASE 2~~")
    void test2() {
        log.debug("[test2 시작]");
    }

    @Test
    @Disabled
    void test3() {
        log.debug("[test3 시작]");
    }
}
