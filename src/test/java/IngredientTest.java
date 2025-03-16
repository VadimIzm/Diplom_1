import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

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
    public void getPriceTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Неверная цена!", price, ingredient.getPrice(), 0.01f);
    }
    @Test
    public void getNameTest(){
        Ingredient ingredient = new Ingredient(type,name,price);
        String actualName = ingredient.getName();
        assertEquals("Неверное название!", name, actualName);
    }
    @Test
    public void getTypeTest(){
        Ingredient ingredient = new Ingredient(type, name,0.01f);
        assertEquals("Неверный тип!", type, ingredient.getType());
    }
}