package game.utilities.streamables.reconstructables;


import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import game.utilities.DecodeableByteArrayInputStream;
import game.utilities.Encoder;
import game.utilities.streamables.Streamables;

//A class extending Reconstructable is a class that implements methods that allow you to construct it from a byte array as well as
//methods to stage changes to it
public interface Reconstructable {


    //threadsafe command line to queue changes to the Element
    public void addPatch(byte[] patch);

    /*
        
    */
    public void executePatches();   

    public static Reconstructable fromBytes(byte[] bytes) {
        //master method for turning byte arrays back into the Packageable classes that produced them
        //For further documentation on each class see their toBytes method
        try {
            DecodeableByteArrayInputStream byteStream = new DecodeableByteArrayInputStream(new ByteArrayInputStream(bytes));
            switch (Streamables.values()[byteStream.readInt()]) {
                
                default:
                    throw new IllegalArgumentException("Unknown class byteID or Unimplemented case");
            }
        } catch(Exception e) {e.printStackTrace(); return null;}
    }

}