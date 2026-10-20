package game.utilities.packageables.elements;

import game.Server;
import game.utilities.Encoder;
import game.utilities.packageables.Packageable;
import game.utilities.variables.StackInteger;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Element implements Packageable {

    protected List<byte[]> patches = Collections.synchronizedList(new LinkedList<byte[]>());

    public void addPatch(byte[] patch) {patches.add(patch);}

    //variables used to autoassign Keys
    private static StackInteger nextKey = new StackInteger(Integer.MIN_VALUE);
    
    //run-time identifier
    public final int KEY;

    //key of the Element that this Element is the child of
    protected int sourceKey = 0;

    //relates to the asset the element tethers to
    protected long assetKey = 0;
    
    public Element() {
        this.KEY = nextKey.value();
    }

    public Element(long assetKey) {
        this.KEY = nextKey.value();
        setAssetKey(assetKey);
    }

    public Element(int sourceKey) {
        this.KEY = nextKey.value();
        setSourceKey(sourceKey);
    }

    public Element(int KEY, int sourceKey, long assetKey) {
        this.KEY = KEY;
        setAssetKey(assetKey);
        setSourceKey(sourceKey);
    }

    public long getAssetKey() {return this.assetKey;}

    protected void setAssetKey(long assetKey) {
        this.assetKey = assetKey;
    }

    public byte[] patchReplaceAssetKey(long assetKey) {
        byte[] update = Packageable.createPatch(Packageable.Package.Element.ordinal(), Packageable.Variable.AssetKey.ordinal(), Packageable.Patch.Replace.byteID, ((Object)assetKey));

        addPatch(update);

        return update;
    }

    public boolean containsKey(int searchKey) {
        int key = Server.instance.get(searchKey).sourceKey;
        while (key != 0) {
            Element ancestor = Server.instance.get(key);
            if (ancestor.KEY == this.KEY) return true;
            if (ancestor.sourceKey == 0) return false;
            key = ancestor.sourceKey;
        }
        return false;
    }

    

    public int getSourceKey() {
        return this.sourceKey;
    }

    protected void setSourceKey(int sourceKey) {
        if (sourceKey == 0) {this.sourceKey = 0; return;}
        if (this.KEY == sourceKey) throw new IllegalArgumentException("An Element cannot be it's own source");
        if (containsKey(sourceKey)) throw new IllegalArgumentException("An Element's child cannot be it's ancestor");
        this.sourceKey = sourceKey;
    }

    public byte[] patchReplaceSourceKey(long sourceKey) {
        byte[] update = Packageable.createPatch(Packageable.Package.Element.ordinal(), Packageable.Variable.SourceKey.ordinal(), Packageable.Patch.Replace.byteID, ((Object)sourceKey));

        addPatch(update);

        return update;
    }

    public byte[] toBytes() {
        return Encoder.concatArrays(new byte[][] {
            Encoder.encodeInt(KEY),
            Encoder.encodeInt(sourceKey),
            Encoder.encodeLong(assetKey)
        });
    }

    @Override 
    public int getPackageID() {return Packageable.Package.Element.ordinal();}

    @Override
    public boolean executePatch() {
        for (int i = 0; i < patches.size(); i ++) {
            
        }
    }

}
