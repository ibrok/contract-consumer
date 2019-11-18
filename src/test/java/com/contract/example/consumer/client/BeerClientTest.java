package com.contract.example.consumer.client;

import static java.util.Arrays.stream;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@SpringBootTest
@Import(BeerClientTestConfiguration.class)
//@AutoConfigureStubRunner(ids = "com.contract.example:producer-app:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@AutoConfigureStubRunner
class BeerClientTest {

    @Autowired
    private BeerClient sut;

    @Test
    public void test_getBeerMenu() {
        BeerMenu beerMenu = sut.getBeerMenu();
        assertThat(beerMenu.getBeerList()).isNotEmpty();
        beerMenu.getBeerList().forEach(beer -> {
            assertThat(beer.getBrand()).isNotBlank();
            assertThat(beer.getPercentage()).isNotNegative();
            assertThat(beer.getType()).isNotNull();
        });
    }

    @Test
    public void test_getBeerMenu_with_type() {
        stream(BeerType.values()).forEach(type -> {
            BeerMenu beerMenu = sut.getBeerMenu(type);
            beerMenu.getBeerList().forEach(beer ->
                    assertThat(beer.getType()).isEqualTo(type));
        });
    }
}

@TestConfiguration
class BeerClientTestConfiguration {

    @Autowired
    StubFinder stubFinder;

    @Bean
    @Primary
    public BeerClient beerclient() {
        String stubUrl = stubFinder.findStubUrl("com.contract.example:producer-app").toExternalForm();
        return new BeerClient(stubUrl);
    }

}
