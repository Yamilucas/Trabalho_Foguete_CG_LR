package foguete;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.Light;
import javax.media.j3d.Locale;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.media.j3d.VirtualUniverse;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;


public class GrafoCena extends JFrame implements KeyListener{
    public VirtualUniverse universo;
    public Locale locale;
    public Canvas3D canvas3D;
    public Foguete foguete;
    boolean screenMode = false;

    public GrafoCena() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dimension.width, dimension.height);
        if (screenMode) {
            this.setUndecorated(true);
        } else {
            this.setTitle("UNIFOR - Foguete - Trabalho de Lucas Eufrásio e Rodrian");
        }
        addKeyListener(this);

        universo = new VirtualUniverse();
        locale = new Locale(universo);
        
        /*Obtém caracteristicas do dispositivo gráfico utilizado: monitor*/
        GraphicsConfigTemplate3D g3d = new GraphicsConfigTemplate3D();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getBestConfiguration(g3d);

        /*Cria o objeto canvas3D*/
        canvas3D = new Canvas3D(gc);
        canvas3D.setSize(dimension.width, dimension.height);

        /*Adicionando o subgrafo de visualização*/ 
        locale.addBranchGraph(grafoVisualizacao()); 
        locale.addBranchGraph(grafoContexto());
        
        /*Adiciona o canvas3D ao tratador de eventos do teclado*/
        canvas3D.addKeyListener(this);
        this.getContentPane().add(canvas3D);

        /*Trata o botao fechar no caso de modo janela*/
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.show();
    }

    /*Método que cria o subgrafo de visualização*/
    public BranchGroup grafoVisualizacao() {
        BranchGroup visualBG = new BranchGroup();
        View visual = new View();
        ViewPlatform visualPlataform = new ViewPlatform();
        PhysicalBody physicalBody = new PhysicalBody(new Point3d(0, 0, 0), new Point3d(0, 0, 0));
        PhysicalEnvironment ambiente = new PhysicalEnvironment();

        /*Adiciona viewPlatform ao view*/ 
        visual.attachViewPlatform(visualPlataform); 
        visual.setPhysicalBody(physicalBody); 
        
        /*Adiciona physicalEnvironment ao view*/ 
        visual.setPhysicalEnvironment(ambiente); 
        
        /*Adiciona canvas ao view*/ 
        visual.addCanvas3D(canvas3D);

        Transform3D transform3D = new Transform3D(); 
        transform3D.set(new Vector3f(0.0f, 0.0f, 15.0f)); 
        TransformGroup visualPlatformTG = new TransformGroup(transform3D); 
        visualPlatformTG.addChild(visualPlataform);
        
        /*Adiciona o TransformGroup ao BranchGroup*/
        visualBG.addChild(visualPlatformTG);

        /*Retorna o BranchGroup contendo o subgrafo de visualização*/
        return visualBG;
    }

    /*Metodo que cria o subgrafo de contexto*/
    public BranchGroup grafoContexto() {

        BranchGroup contextoBG = new BranchGroup();

        contextoBG.addChild(criaLuz());
        
        foguete = new Foguete(new Vector3f(0.0f, 0.0f, 0.0f));
        contextoBG.addChild(foguete.getFoguete());

        return contextoBG;
    }
    
    //Método de luz no foguete
    public Light criaLuz() {
        DirectionalLight light = new DirectionalLight(
                true,
                new Color3f(1.0f, 1.0f, 1.0f),
                new Vector3f(-0.3f, 0.2f, -1.0f)
        );

        light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

        return light;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Transformações ao apertar a tecla S/W
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            Transform3D t3dTempo = new Transform3D();
            t3dTempo.rotX(Math.toRadians(20));
            foguete.setTransformacao(t3dTempo);
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            Transform3D t3dTempo = new Transform3D();
            t3dTempo.rotX(Math.toRadians(-20));
            foguete.setTransformacao(t3dTempo);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}