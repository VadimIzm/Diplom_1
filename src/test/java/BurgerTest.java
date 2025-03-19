import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
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
    Bun mockBun;
    private IngredientType type;
    private String name;
    private float price;

    @Mock
    private Ingredient mockIngredientFilling;
    @Mock
    private Ingredient mockIngredientSauce;

    @InjectMocks
    private Burger burger;

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
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest(){
        burger.addIngredient(mockIngredientFilling);
        assertFalse("Ошибка!", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest(){
        burger.addIngredient(mockIngredientFilling);
        burger.removeIngredient(0);
        assertTrue("Ошибка!", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient(){
        burger.addIngredient(mockIngredientFilling); //вставлено
        burger.addIngredient(mockIngredientSauce); //вставлено
        burger.moveIngredient(0, 1);
        assertEquals("Ошибка!", burger.ingredients.get(0), mockIngredientSauce);
    }

    @Test
    public void getPriceTest(){
        burger.addIngredient(new Ingredient(type, name, price));
        burger.addIngredient(new Ingredient(type, name, price));
        assertEquals(0.0, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest(){
        burger.setBuns(mockBun);
        String result = String.format("(==== Флюоресцентная булка R2-D3 ====)%n" + "= filling Говяжий метеорит (отбивная) =%n" + "(==== Флюоресцентная булка R2-D3 ====)%n" +"%n" +"Price: 400,000000%n");
        burger.addIngredient(mockIngredientFilling);
        Mockito.when(mockBun.getPrice()).thenReturn(100F);
        Mockito.when(mockIngredientFilling.getPrice()).thenReturn(200F);
        Mockito.when(mockBun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(mockIngredientFilling.getName()).thenReturn("Говяжий метеорит (отбивная)");
        Mockito.when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        Assert.assertEquals(result, burger.getReceipt());
    }
}