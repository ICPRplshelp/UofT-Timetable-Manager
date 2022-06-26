import org.example.requisitechecker.usecases.internal.BracketDealer;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBracketDealer {

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets1(){
        BracketDealer bd = new BracketDealer();
        String str = "(MAT)(APM)(PB)(CSC)";
        String output = bd.removeHuggingBrackets(str);
        assertEquals(str, output);
    }

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets2(){
        BracketDealer bd = new BracketDealer();
        String str = "[MAT](APM)(PB)[CSC]";
        String  output = bd.removeHuggingBrackets(str);
        assertEquals(str, output);
    }

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets3(){
        BracketDealer bd = new BracketDealer();
        String str = "[CSC(MAT)(APM)[BCH]]";
        String output = bd.removeHuggingBrackets(str);
        assertEquals(str.substring(1, str.length() - 1), output);
    }
}
