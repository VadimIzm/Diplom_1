import org.junit.Test;
import praktikum.IngredientType;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void sauceTest(){
        assertNotNull(IngredientType.SAUCE);
    }

    @Test
    public void fillingTest() {
        assertNotNull(IngredientType.FILLING);
    }
}