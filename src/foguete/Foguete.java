
package foguete;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;



public class Foguete {
    public Cylinder corpo, base1, base2, base3, base4;
    public Sphere ponta, motor;
    public TransformGroup tg, tgCorpo, tgBase1, tgBase2, tgBase3, tgBase4, tgPonta, tgMotor;
    public Transform3D t3d, t3dCorpo, t3dBase1, t3dBase2, t3dBase3, t3dBase4, t3dPonta, t3dMotor;

    public Foguete(Vector3f posicao) {

        tg = new TransformGroup();
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        t3d = new Transform3D();
        t3d.set(posicao);
        tg.setTransform(t3d);

        Color3f corPrincipal = new Color3f(1.0f, 0.3f, 0.0f);
        Color3f corMotor = new Color3f(1.0f, 1.0f, 1.0f);

        // Corpo
        corpo = new Cylinder(0.5f, 2.0f, criarAparencia(corPrincipal));
        tgCorpo = new TransformGroup();
        t3dCorpo = new Transform3D();
        tgCorpo.setTransform(t3dCorpo);
        tgCorpo.addChild(corpo);
        tg.addChild(tgCorpo);

        // Ponta
        ponta = new Sphere(0.5f, criarAparencia(corPrincipal));
        tgPonta = new TransformGroup();
        t3dPonta = new Transform3D();
        t3dPonta.set(new Vector3d(0.0, 1.00, 0.0));
        tgPonta.setTransform(t3dPonta);
        tgPonta.addChild(ponta);
        tg.addChild(tgPonta);

        // Motor
        motor = new Sphere(0.45f, criarAparencia(corMotor));
        tgMotor = new TransformGroup();
        t3dMotor = new Transform3D();
        t3dMotor.set(new Vector3d(0.0, -1.00, 0.0));
        tgMotor.setTransform(t3dMotor);
        tgMotor.addChild(motor);
        tg.addChild(tgMotor);

        // Base 1
        base1 = new Cylinder(0.05f, 1.5f, criarAparencia(corPrincipal));
        tgBase1 = new TransformGroup();
        t3dBase1 = new Transform3D();
        t3dBase1.set(new Vector3d(0.5, -1.10, 0.0));
        //t3dBase1.rotZ(Math.toRadians(90));
        tgBase1.setTransform(t3dBase1);
        tgBase1.addChild(base1);
        tg.addChild(tgBase1);

        // Base 2
        base2 = new Cylinder(0.05f, 1.5f, criarAparencia(corPrincipal));
        tgBase2 = new TransformGroup();
        t3dBase2 = new Transform3D();
        t3dBase2.set(new Vector3d(-0.5, -1.10, 0.0));
        //t3dBase2.rotZ(Math.toRadians(90));
        tgBase2.setTransform(t3dBase2);
        tgBase2.addChild(base2);
        tg.addChild(tgBase2);

        // Base 3
        base3 = new Cylinder(0.05f, 1.5f, criarAparencia(corPrincipal));
        tgBase3 = new TransformGroup();
        t3dBase3 = new Transform3D();
        t3dBase3.set(new Vector3d(0.0, -1.10, 0.5));
        //t3dBase3.rotX(Math.toRadians(90));
        tgBase3.setTransform(t3dBase3);
        tgBase3.addChild(base3);
        tg.addChild(tgBase3);

        // Base 4
        base4 = new Cylinder(0.05f, 1.5f, criarAparencia(corPrincipal));
        tgBase4 = new TransformGroup();
        t3dBase4 = new Transform3D();
        t3dBase4.set(new Vector3d(0.0, -1.10, -0.5));
        //t3dBase4.rotX(Math.toRadians(90));
        tgBase4.setTransform(t3dBase4);
        tgBase4.addChild(base4);
        tg.addChild(tgBase4);
    }

    public Appearance criarAparencia(Color3f col) {
        Appearance ap = new Appearance();
        Material ma = new Material();
        ma.setDiffuseColor(col);
        ma.setEmissiveColor(col);
        ap.setMaterial(ma);
        return ap;
    }

    public TransformGroup getFoguete() {
        return tg;
    }

    public void setTransformacao(Transform3D t3dTemp) {
        t3d.mul(t3dTemp);
        tg.setTransform(t3d);
    }
}