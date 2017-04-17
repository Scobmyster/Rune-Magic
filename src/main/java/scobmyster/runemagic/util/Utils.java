package scobmyster.runemagic.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scobmyster.runemagic.Reference;

import java.util.Iterator;
import java.util.List;

public class Utils
{
    private static Logger logger;

    public static Logger getLogger()
    {
        if(logger == null)
        {
            logger = LogManager.getFormatterLogger(Reference.MODID);
        }
        return logger;
    }



}
