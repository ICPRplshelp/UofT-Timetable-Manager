import org.example.requisitechecker.usecases.internal.BracketDealer;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBracketDealer {

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets1(){
        var bd = new BracketDealer();
        var str = "(MAT)(APM)(PB)(CSC)";
        var output = bd.removeHuggingBrackets(str);
        assertEquals(str, output);
    }

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets2(){
        var bd = new BracketDealer();
        var str = "[MAT](APM)(PB)[CSC]";
        var output = bd.removeHuggingBrackets(str);
        assertEquals(str, output);
    }

    @Test(timeout = 50)
    public void testRemoveHuggingBrackets3(){
        var bd = new BracketDealer();
        var str = "[CSC(MAT)(APM)[BCH]]";
        var output = bd.removeHuggingBrackets(str);
        assertEquals(str.substring(1, str.length() - 1), output);
    }
}
