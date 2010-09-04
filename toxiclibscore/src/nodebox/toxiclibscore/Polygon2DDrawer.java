package nodebox.toxiclibscore;

import nodebox.node.*;
import nodebox.util.ProcessingSupport;
import processing.core.PApplet;
import processing.core.PConstants;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;

import java.awt.*;
import java.util.Iterator;

public class Polygon2DDrawer extends RenderingNode {

    public final Polygon2DPort pPolygon = new Polygon2DPort(this, "polygon", Port.Direction.INPUT);
    public final ColorPort pFill = new ColorPort(this, "color", Port.Direction.INPUT, Color.WHITE);
    public final ColorPort pStroke = new ColorPort(this, "stroke", Port.Direction.INPUT, Color.BLACK);
    public final FloatPort pStrokeWeight = new FloatPort(this, "strokeWeight", Port.Direction.INPUT, 1f);

    public Polygon2DDrawer() {
        setAttribute(DESCRIPTION_ATTRIBUTE, "Draw a 2-dimensional polygon.");
        pStrokeWeight.set(1f);
    }

    @Override
    public void execute(Context context, double time) {
        Polygon2D polygon = pPolygon.get();
        if (polygon == null) return;
        PApplet g = context.getApplet();
        g.pushStyle();
        ProcessingSupport.setStyle(g, pFill, pStroke, pStrokeWeight);
        Iterator<? extends Vec2D> iterator = pPolygon.get().vertices.iterator();
        g.beginShape(PConstants.POLYGON);
        while (iterator.hasNext()) {
            Vec2D v = iterator.next();
            g.vertex(v.x, v.y);
        }
        g.endShape(PConstants.CLOSE);
        g.popStyle();
    }
}