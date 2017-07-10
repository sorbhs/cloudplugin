package ut.com.mindtree.digital;

import org.junit.Test;
import com.mindtree.digital.api.MyPluginComponent;
import com.mindtree.digital.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}