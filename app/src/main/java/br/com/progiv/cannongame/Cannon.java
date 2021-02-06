package br.com.progiv.cannongame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

// Canhao
public class Cannon {
    private int baseRadius; // Raio da base do canho
    private int barrelLegth; // Comprimento do cano
    private Point barrelEnd = new Point(); // Ponto estremo do cano do canhão
    private double barrelAngle; // Angulo do cano do canhão
    private CannonBall cannonBall; // A bala do canhão
    private Paint paint = new Paint(); // Objeto desenhar o canhao
    private CannonView view; // View

    public Cannon(CannonView view, int baseRadius, int barrelLegth, int barrelWidth){
        this.view = view;
        this.baseRadius = baseRadius;
        this.barrelLegth = barrelLegth;
        paint.setStrokeWidth(barrelWidth); // Configura a largura do cano
        paint.setColor(Color.BLACK);
        align(Math.PI / 2); // Cano do canhão voltado diretamente para direita
    }

    // Metodo Align que vai alinhar o cano do canhão com o angulo
    public void align(double barrelAngle){
        this.barrelAngle = barrelAngle;
        barrelEnd.x = (int)(barrelLegth * Math.sin(barrelAngle));
        barrelEnd.y = (int)(-barrelLegth * Math.cos(barrelAngle)) + view.getScreenHeight() / 2;
    }

    // Criar e dispara a bala na direção apontada pelo canhão
    public void fireCannonBall(){
        // Calcular o componente X de velocidade da bala
        int velocityX = (int)(CannonView.CANNONBALL_SPEED_PERCENT * view.getScreenWidth() * Math.sin(barrelAngle));
        // Calcular o componente Y de velocidade da bala
        int velocityY = (int)(CannonView.CANNONBALL_SPEED_PERCENT * view.getScreenWidth() * -Math.cos(barrelAngle));
        // Calcular o raio da bala
        int radius = (int)(view.getScreenHeight() * CannonView.CANNONBALL_RADIUS_PERCENT);
        // Constroi a bala e a posiciona no canhão
        cannonBall = new CannonBall(
                view,
                Color.BLACK,
                CannonView.CANNON_SOUND_ID,
                -radius,
                view.getScreenHeight() / 2 - radius,
                radius,
                velocityX,
                velocityY
        );
        // Reproduz o som de disparo
        cannonBall.playSound();
    }

    // Desenhar o canhão no objeto canvas
    public void draw(Canvas canvas){
        // Desenhar o cano do canhão
        canvas.drawLine(0, view.getScreenHeight() / 2, barrelEnd.x, barrelEnd.y, paint);
        // Desenhar a base do canhão
        canvas.drawCircle(0, (int)view.getScreenHeight() / 2, (int)baseRadius, paint);
    }

    // Retorna a bala disparada pelo canhão
    public CannonBall getCannonBall(){
        return cannonBall;
    }

    // Remover a bala do jogo
    public void removeCannonBall(){
        cannonBall = null;
    }
}
