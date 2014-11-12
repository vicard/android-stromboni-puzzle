package com.bravelittlescientist.android_puzzle_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.view.*;
import android.widget.Toast;

import java.util.Random;

public class PuzzleCompactSurface extends SurfaceView implements SurfaceHolder.Callback {

    /** Surface Components **/
    private PuzzleThread gameThread;
    private volatile boolean running = false;
    private int found = -1;

    /** Puzzle and Canvas **/
    private int IMAGE_WIDTH;
    private int IMAGE_HEIGHT;
    private int MAX_PUZZLE_PIECE_SIZE = 100; //Fixe le maximum des tailles de pices modifiables dans config
    private int PUZZLE_WIDTH = 150;
    private int PUZZLE_HEIGHT = 75;
    private int LOCK_ZONE_LEFT = 25; //Décalage de l'image en x
    private int LOCK_ZONE_TOP = 25; //Decalage de l'image en y

    private int NB_PIECES_TO_REPLACE = 5;

    private JigsawPuzzle puzzle;
    private BitmapDrawable[] scaledSurfacePuzzlePieces;
    private Rect[] scaledSurfaceTargetBounds;

    private BitmapDrawable backgroundImage;
    private Paint framePaint;

    public PuzzleCompactSurface(Context context) {
        super(context);

        getHolder().addCallback(this);

        gameThread = new PuzzleThread(getHolder(), context, this);

        setFocusable(true);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        IMAGE_WIDTH = size.x *2/3 - 50;
        IMAGE_HEIGHT= size.y - 100;

    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) gameThread.pause();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
        gameThread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread.setRunning(true);
        gameThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
       boolean retry = true;
        gameThread.setRunning(false);
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }


    public void setPuzzle(JigsawPuzzle jigsawPuzzle) {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point outSize = new Point();
        display.getSize(outSize);

        puzzle = jigsawPuzzle;


        PUZZLE_WIDTH = IMAGE_WIDTH / puzzle.getPuzzleDimensions()[2];
        PUZZLE_HEIGHT= IMAGE_HEIGHT/ puzzle.getPuzzleDimensions()[3];

        Random r = new Random();

        if (puzzle.isBackgroundTextureOn()) {
            backgroundImage = new BitmapDrawable(puzzle.getBackgroundTexture());
            backgroundImage.setBounds(0, 0, outSize.x, outSize.y);
        }
        framePaint = new Paint();
        framePaint.setColor(Color.BLACK);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setTextSize(20);

        /** Initialize drawables from puzzle pieces **/
        Bitmap[] originalPieces = puzzle.getPuzzlePiecesArray();
        int[][] positions = puzzle.getPuzzlePieceTargetPositions();
        int[] dimensions = puzzle.getPuzzleDimensions();


        scaledSurfacePuzzlePieces = new BitmapDrawable[originalPieces.length];
        scaledSurfaceTargetBounds = new Rect[originalPieces.length];


        for (int i = 0; i < originalPieces.length; i++) {

            scaledSurfacePuzzlePieces[i] = new BitmapDrawable(originalPieces[i]);

            // Top left is (0,0) in Android canvas
            int topLeftX = r.nextInt(outSize.x - PUZZLE_WIDTH);      // MAX_PUZZLE_PIECE_SIZE); //Place les images au hasard en x et y
            int topLeftY = r.nextInt(outSize.y - 2 * PUZZLE_HEIGHT); //MAX_PUZZLE_PIECE_SIZE);

            scaledSurfacePuzzlePieces[i].setBounds(topLeftX, topLeftY,
                    topLeftX + PUZZLE_WIDTH,    //MAX_PUZZLE_PIECE_SIZE,
                    topLeftY + PUZZLE_HEIGHT); //MAX_PUZZLE_PIECE_SIZE);//Mettre la bonne position ici pour chaque piece
        }
        int cpt=0;
        int cptx=0;
        int cpty=0;
        int nbPieces = puzzle.getNbPieces();
        int nbPiecesFix = nbPieces-NB_PIECES_TO_REPLACE;
        Random r2 = new Random();
        int nbPiecesRestantes = NB_PIECES_TO_REPLACE;


            for (int w = 0; w < dimensions[2]; w++) {
                for (int h = 0; h < dimensions[3]; h++) {
                    int targetPiece = positions[w][h];

                    scaledSurfaceTargetBounds[targetPiece] = new Rect(
                            LOCK_ZONE_LEFT + w * PUZZLE_WIDTH,//MAX_PUZZLE_PIECE_SIZE,
                            LOCK_ZONE_TOP + h * PUZZLE_HEIGHT,//MAX_PUZZLE_PIECE_SIZE,
                            LOCK_ZONE_LEFT + w * PUZZLE_WIDTH + PUZZLE_WIDTH,  //MAX_PUZZLE_PIECE_SIZE + MAX_PUZZLE_PIECE_SIZE,
                            LOCK_ZONE_TOP + h * PUZZLE_HEIGHT + PUZZLE_HEIGHT);//MAX_PUZZLE_PIECE_SIZE + MAX_PUZZLE_PIECE_SIZE);

                    if (cpt<nbPiecesFix) { // 5 pieces seront placées de façons aléatoire
                        if(r2.nextInt(2)==1 || nbPiecesRestantes==0) {
                            scaledSurfacePuzzlePieces[targetPiece].setBounds(
                                    LOCK_ZONE_LEFT + w * PUZZLE_WIDTH,//MAX_PUZZLE_PIECE_SIZE,
                                    LOCK_ZONE_TOP + h * PUZZLE_HEIGHT,//MAX_PUZZLE_PIECE_SIZE,
                                    LOCK_ZONE_LEFT + w * PUZZLE_WIDTH + PUZZLE_WIDTH,  //MAX_PUZZLE_PIECE_SIZE + MAX_PUZZLE_PIECE_SIZE,
                                    LOCK_ZONE_TOP + h * PUZZLE_HEIGHT + PUZZLE_HEIGHT);//MAX_PUZZLE_PIECE_SIZE + MAX_PUZZLE_PIECE_SIZE);
                            puzzle.setPieceLocked(targetPiece, true);
                            cpt++;
                        } else{
                            if(cptx>1){
                                cpty++;
                                cptx=0;
                            }
                            scaledSurfacePuzzlePieces[targetPiece].setBounds(
                                    IMAGE_WIDTH+LOCK_ZONE_LEFT+LOCK_ZONE_LEFT+(PUZZLE_WIDTH+LOCK_ZONE_LEFT)*cptx,
                                    LOCK_ZONE_TOP+(PUZZLE_HEIGHT+LOCK_ZONE_TOP)*cpty,
                                    IMAGE_WIDTH+LOCK_ZONE_LEFT+LOCK_ZONE_LEFT + PUZZLE_WIDTH+(PUZZLE_WIDTH+LOCK_ZONE_LEFT)*cptx,
                                    LOCK_ZONE_TOP + PUZZLE_HEIGHT+(PUZZLE_HEIGHT+LOCK_ZONE_TOP)*cpty);
                            cptx++;
                            nbPiecesRestantes--;
                        }

                     }
                    else {
                        if(cptx>1){
                            cpty++;
                            cptx=0;
                        }
                        scaledSurfacePuzzlePieces[targetPiece].setBounds(
                                IMAGE_WIDTH+LOCK_ZONE_LEFT+LOCK_ZONE_LEFT+(PUZZLE_WIDTH+LOCK_ZONE_LEFT)*cptx,
                                LOCK_ZONE_TOP+(PUZZLE_HEIGHT+LOCK_ZONE_TOP)*cpty,
                                IMAGE_WIDTH+LOCK_ZONE_LEFT+LOCK_ZONE_LEFT + PUZZLE_WIDTH+(PUZZLE_WIDTH+LOCK_ZONE_LEFT)*cptx,
                                LOCK_ZONE_TOP + PUZZLE_HEIGHT+(PUZZLE_HEIGHT+LOCK_ZONE_TOP)*cpty);
                        cptx++;
                        nbPiecesRestantes--;
                    }
                 }
            }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvas!=null) {
            canvas.drawColor(Color.BLACK);

            if (puzzle.isBackgroundTextureOn()) {
                backgroundImage.draw(canvas);
            }
            canvas.drawRect(LOCK_ZONE_LEFT, LOCK_ZONE_TOP, LOCK_ZONE_LEFT + IMAGE_WIDTH, LOCK_ZONE_TOP + IMAGE_HEIGHT, framePaint);

            for (int bmd = 0; bmd < scaledSurfacePuzzlePieces.length; bmd++) {
                if (puzzle.isPieceLocked(bmd)) {
                    scaledSurfacePuzzlePieces[bmd].draw(canvas);
                }
            }

            for (int bmd = 0; bmd < scaledSurfacePuzzlePieces.length; bmd++) {
                if (!puzzle.isPieceLocked(bmd)) {
                    scaledSurfacePuzzlePieces[bmd].draw(canvas);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int xPos =(int) event.getX();
        int yPos =(int) event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < scaledSurfacePuzzlePieces.length; i++) {
                    Rect place = scaledSurfacePuzzlePieces[i].copyBounds();

                    if (place.contains(xPos, yPos) && !puzzle.isPieceLocked(i)) {
                        found = i;

                        // Trigger puzzle piece picked up
                        puzzle.onJigsawEventPieceGrabbed(found, place.left, place.top);

                    }
                }
                break;


            case MotionEvent.ACTION_MOVE:
                if (found >= 0 && found < scaledSurfacePuzzlePieces.length && !puzzle.isPieceLocked(found)) {
                    // Lock into position...
                    if (scaledSurfaceTargetBounds[found].contains(xPos, yPos) ) {
                            scaledSurfacePuzzlePieces[found].setBounds(scaledSurfaceTargetBounds[found]);
                            puzzle.setPieceLocked(found, true);

                            // Trigger jigsaw piece events
                            puzzle.onJigsawEventPieceMoved(found,
                                    scaledSurfacePuzzlePieces[found].copyBounds().left,
                                    scaledSurfacePuzzlePieces[found].copyBounds().top);
                            puzzle.onJigsawEventPieceDropped(found,
                                    scaledSurfacePuzzlePieces[found].copyBounds().left,
                                    scaledSurfacePuzzlePieces[found].copyBounds().top);

                        NB_PIECES_TO_REPLACE--;
                        if (NB_PIECES_TO_REPLACE==0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("VICTOIRE");
                            builder.setMessage("Bravo, tu as finis le puzzle !");
                            builder.setPositiveButton("Quitter le puzzle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent i2 = new Intent(getContext(), LaunchActivity.class);
                                    getContext().startActivity(i2);
                                    System.exit(0);



                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    } else {
                        Rect rect = scaledSurfacePuzzlePieces[found].copyBounds();

                        rect.left = xPos - PUZZLE_WIDTH/2;//MAX_PUZZLE_PIECE_SIZE/2;
                        rect.top = yPos - PUZZLE_HEIGHT/2;//MAX_PUZZLE_PIECE_SIZE/2;
                        rect.right = xPos + PUZZLE_WIDTH/2;//MAX_PUZZLE_PIECE_SIZE/2;
                        rect.bottom = yPos + PUZZLE_HEIGHT/2;//MAX_PUZZLE_PIECE_SIZE/2;
                        scaledSurfacePuzzlePieces[found].setBounds(rect);

                        // Trigger jigsaw piece event
                        puzzle.onJigsawEventPieceMoved(found, rect.left, rect.top);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                // Trigger jigsaw piece event
                if (found >= 0 && found < scaledSurfacePuzzlePieces.length) {
                    puzzle.onJigsawEventPieceDropped(found, xPos, yPos);
                }
                found = -1;
                break;

        }


        return true;
    }

    public PuzzleThread getThread () {
        return gameThread;
    }
}
