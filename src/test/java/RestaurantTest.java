import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockedRestaurant = Mockito.spy(restaurant);

        LocalTime validTime = LocalTime.parse("16:00:00");

        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(validTime);

        assertTrue(mockedRestaurant.isRestaurantOpen());

        Mockito.verify(mockedRestaurant, Mockito.times(1)).getCurrentTime();
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant mockedRestaurant = Mockito.spy(restaurant);

        LocalTime invalidTime = LocalTime.parse("10:00:00");

        Mockito.when(mockedRestaurant.getCurrentTime()).thenReturn(invalidTime);

        assertFalse(mockedRestaurant.isRestaurantOpen());

        Mockito.verify(mockedRestaurant, Mockito.times(1)).getCurrentTime();
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    @Test
    public void total_amount_must_be_0_if_no_items_are_selected_from_the_menu() {
        List<String> selectedItems = new ArrayList<String>();

        int calculatedAmount = restaurant.calculateTotalAmount(selectedItems);

        assertEquals(0, calculatedAmount);
    }


    @Test
    public void total_amount_should_be_350_if_two_items_of_???_150_and_???_200_are_added(){
        List<String> selectedItems = new ArrayList<String>();
        restaurant.addToMenu("ABC", 150);
        restaurant.addToMenu("DEF", 200);

        Item item1 = new Item("ABC", 150);
        Item item2 = new Item("DEF", 200);
        selectedItems.add(item1.getName());
        selectedItems.add(item2.getName());

        int calculatedAmount = restaurant.calculateTotalAmount(selectedItems);

        assertEquals(350, calculatedAmount);
    }

}