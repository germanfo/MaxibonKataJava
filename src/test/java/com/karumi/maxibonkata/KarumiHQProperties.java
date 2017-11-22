package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(JUnitQuickcheck.class)
public class KarumiHQProperties {

    private KarumiHQs karumiHQs;

    @Before
    public void setUp(){
        karumiHQs = new KarumiHQs(mock(Chat.class));
    }

    @Property public void theFridgeShouldNeverHaveLessThanTwoMaxiBonsAfterDeveloperEats(@From(DevelopersGenerator.class) Developer developer) {
        System.out.println("DEVELOPER.eat = " + developer.getNumberOfMaxibonsToGrab() + "; Frdige.left = " + karumiHQs.getMaxibonsLeft());

        karumiHQs.openFridge(developer);

        System.out.println("Fridge.leftAfterEat = " + karumiHQs.getMaxibonsLeft() + "\n");



        assertTrue(karumiHQs.getMaxibonsLeft() >= 2);
    }


    @Property public void theFridgeShouldNeverHaveLessThanTwoMaxiBonsAfterNotSoHungryDeveloperEats(@From(NotSoHungryDevelopersGenerator.class) Developer developer) {
        System.out.println("DEVELOPER.eat = " + developer.getNumberOfMaxibonsToGrab() + "; Frdige.left = " + karumiHQs.getMaxibonsLeft());

        karumiHQs.openFridge(developer);

        System.out.println("Fridge.leftAfterEat = " + karumiHQs.getMaxibonsLeft() + "\n");



        assertTrue(karumiHQs.getMaxibonsLeft() >= 2);
    }

    @Property public void theFridgeShouldHave(@From(NotSoHungryDevelopersGenerator.class) Developer developer){
        System.out.println("DEVELOPER.eat = " + developer.getNumberOfMaxibonsToGrab() + "; Fridge.left = " + karumiHQs.getMaxibonsLeft());

        int maxibonsInFridgeBeforeEat = karumiHQs.getMaxibonsLeft();

        karumiHQs.openFridge(developer);

       int numMaxibonExpected = getMaxibonsAfterOpeningTheFridge(maxibonsInFridgeBeforeEat, developer.getNumberOfMaxibonsToGrab());

        System.out.println("Fridge.leftAfterEat = " + karumiHQs.getMaxibonsLeft() + "\n");

        assertTrue(numMaxibonExpected == karumiHQs.getMaxibonsLeft());
    }

    private int getMaxibonsAfterOpeningTheFridge(int maxibonsInFridgeBeforeEat, int numberOfMaxibonsToGrab) {
        int maxibonsLeft = maxibonsInFridgeBeforeEat;
        maxibonsLeft -= numberOfMaxibonsToGrab;

        if (maxibonsLeft < 0){
            maxibonsLeft = 0;
        }
        if (maxibonsLeft <= 2){
            maxibonsLeft += 10;
        }
        return maxibonsLeft;
    }


    @Property public void theFridgeShouldNeverHaveLessThanTwoMaxiBonsAfterMoreThanOneNotSoHungryDeveloperEats(List<@From(DevelopersGenerator.class) Developer> developerList){
       // System.out.println("DEVELOPER.eat = " + developer.getNumberOfMaxibonsToGrab() + "; Frdige.left = " + karumiHQs.getMaxibonsLeft());

        System.out.println("Number of developers to eat = " + developerList.size());
        System.out.println("Fridge.leftBeforeEat = " + karumiHQs.getMaxibonsLeft() + "\n");
        karumiHQs.openFridge(developerList);
        System.out.println("Fridge.leftAfterEat = " + karumiHQs.getMaxibonsLeft() + "\n");


        assertTrue(karumiHQs.getMaxibonsLeft() >= 2);
    }


    @Property public void theFridgeShouldHaveNMaxibonsCalculatedAfterEatingSomeDevelopers(List<@From(NotSoHungryDevelopersGenerator.class) Developer> developerList){

        int numMaxibonExpected = 10;
        for (Developer dev : developerList) {
            numMaxibonExpected = getMaxibonsAfterOpeningTheFridge(numMaxibonExpected, dev.getNumberOfMaxibonsToGrab());
        }

        karumiHQs.openFridge(developerList);

        System.out.println("Fridge.leftAfterEat = " + karumiHQs.getMaxibonsLeft() + "\n");

        assertTrue(numMaxibonExpected == karumiHQs.getMaxibonsLeft());
    }
}
