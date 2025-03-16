import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;
    private IngredientType type;
    private String name;
    private float price;

    @Parameterized.Parameters
    public static Object[] data(){
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 300},
                {IngredientType.SAUCE,"",0},
                {IngredientType.SAUCE,"",-50},
        };
    }

    @Test
    public void setBunsTest(){
        Bun bun = new Bun("black bun", 100);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        assertFalse("Ошибка!", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        burger.removeIngredient(0);
        assertTrue("Ошибка!", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient(){
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        burger.moveIngredient(0, 1);
        String expectedResult = "chili sauce";
        String actualResult = burger.ingredients.get(0).name;
        assertEquals("Ошибка!", expectedResult, actualResult);
    }

    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(type, name, price));
        burger.addIngredient(new Ingredient(type, name, price));
        assertEquals(0.0, burger.getPrice(), 0.001);
    }

    @Mock
    Ingredient ingredientFill;
    @Test
    public void getReceiptTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        String result = String.format("(==== Флюоресцентная булка R2-D3 ====)%n" + "= filling Говяжий метеорит (отбивная) =%n" + "(==== Флюоресцентная булка R2-D3 ====)%n" +"%n" +"Price: 400,000000%n");
        burger.addIngredient(ingredientFill);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredientFill.getPrice()).thenReturn(200F);
        Mockito.when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientFill.getName()).thenReturn("Говяжий метеорит (отбивная)");
        Mockito.when(ingredientFill.getType()).thenReturn(IngredientType.FILLING);
        Assert.assertEquals(result, burger.getReceipt());
    }
}