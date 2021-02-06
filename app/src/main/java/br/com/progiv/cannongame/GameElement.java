package br.com.progiv.cannongame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class GameElement {
    protected CannonView view; // A view que contem esse GameElement
    protected Paint paint = new Paint(); // Object Paint para desenhar
    protected Rect shape; // Os limites retangulares do GameElement
    protected float velocityY; // Velocidade Vertical
    protected int soundId; // Id do som associado;

    // Construtor
    public GameElement(CannonView view, int color, int soundId, int x, int y, int width, int length, float velocityY){
        this.view = view;
        paint.setColor(color);
        shape = new Rect(x, y, x + width, y + length);
        this.soundId = soundId;
        this.velocityY = velocityY;

    }

    // Atualizar a posição de GameElement e verificar se ha colisões com a parede
    public void update(double interval){
        // Atualizar a posição vertical
        shape.offset(0, (int)(velocityY * interval));

        // Se esse GameElement colide com a parede inverte a direção
        if(shape.top < 0 && velocityY < 0 || shape.bottom > view.getScreenHeight() && velocityY > 0)
            velocityY *= -1;
    }

    // desenhar o objeto canvas
    public void draw(Canvas canvas){
        canvas.drawRect(shape, paint);
    }

    // Reproduzir o som correspondente a esse tipo de objeto
    public void playSound(){
        view.playSound(soundId);
    }
}
