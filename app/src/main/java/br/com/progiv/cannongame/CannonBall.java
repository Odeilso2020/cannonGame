package br.com.progiv.cannongame;

import android.graphics.Canvas;
import android.graphics.Rect;

public class CannonBall extends GameElement {
    private float velocityX;
    private boolean onScreen;

    public CannonBall(
            CannonView view, int color, int soundId, int x, int y,
            int radius, int velocityX, float velocityY
    ){
        super(view, color, soundId, x, y, 2 * radius, 2 * radius, velocityY);
        this.velocityX = velocityX;
        onScreen = true;
    }

    // Metodo que obtem o raio da bala
    public int getRadius(){
        return (shape.right - shape.left) / 2;
    }

    // Testar se a bala colide com o GameElement
    public boolean collidesWith(GameElement element){
        return (Rect.intersects(shape, element.shape) && velocityX > 0);
    }

    // Retornar se a bala esta na tela
    public boolean isOnScreen(){
        return onScreen;
    }

    // Inverter a velocidade horizontal da bala
    public void reverseVelocityX(){
        velocityX *= -1;
    }

    // Metodo update para atualizar a velocidade
    @Override
    public void update(double interval) {
        super.update(interval); // Atualiza a posição vertical da bala;
        // Atualizar a posição horizontal
        shape.offset((int)(velocityX * interval), 0);
        // Se a bala sair da tela
        if(shape.top < 0 || shape.left < 0 || shape.bottom > view.getScreenHeight()
            || shape.right > view.getScreenWidth()){
            onScreen = false; // Configura para remover a bala de canhão da tela
        }
    }

    // Desenha a bala de canhão na tela
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(shape.left + getRadius(), shape.top + getRadius(), getRadius(), paint);
    }
}
