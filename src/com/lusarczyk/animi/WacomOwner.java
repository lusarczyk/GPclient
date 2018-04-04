package com.lusarczyk.animi;

import javafx.scene.Node;
import jpen.PenEvent;
import jpen.PenProvider;
import jpen.owner.AbstractPenOwner;
import jpen.owner.PenClip;
import jpen.owner.PenOwner;
import jpen.provider.osx.CocoaProvider;
import jpen.provider.wintab.WintabProvider;
import jpen.provider.xinput.XinputProvider;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Collection;

public class WacomOwner implements PenOwner {

    private final Node ownerNode;

    private final PenClip penClip = new PenClip() {
        //@Override
        public void evalLocationOnScreen(Point locationOnScreen){
            // The location of this PenClip is always on (0, 0) screen coordinates.
            locationOnScreen.x= (int)ownerNode.getLayoutX();
            locationOnScreen.y=(int)ownerNode.getLayoutY();

        }
        //@Override
        public boolean contains(Point2D.Float point){
            // This PenClip covers all the screen.
            return true;
        }
    };
    private PenManagerHandle penManagerHandle;


    public WacomOwner(Node ownerNode) {
        this.ownerNode = ownerNode;



    }


    @Override
    public Collection<PenProvider.Constructor> getPenProviderConstructors(){
        return Arrays.asList(
                new PenProvider.Constructor[]{
                        // new SystemProvider.Constructor(), //Does not work because it needs a java.awt.Component to register the MouseListener
                        new XinputProvider.Constructor(),
                        new WintabProvider.Constructor(),
                        new CocoaProvider.Constructor()
                }
        );
    }




    @Override
    public PenClip getPenClip() {
        //System.out.println("getPenClip()");
        return penClip;
    }

    @Override
    public boolean isDraggingOut() {
        return false;
    }

    @Override
    public Object evalPenEventTag(PenEvent penEvent) {
        return null;
    }

    @Override
    public boolean enforceSinglePenManager() {
        return false;
    }

    //@Override
    public void setPenManagerHandle(final PenManagerHandle penManagerHandle){
        this.penManagerHandle = penManagerHandle;
        penManagerHandle.setPenManagerPaused(false);
        System.out.println("penManagerHandle = " + penManagerHandle);
    }

}
