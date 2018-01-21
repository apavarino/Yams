package fr.e3ge.yams;


import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class yams extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bg, board;
	ArrayList<Dice> dices;
	Stage stage;
    Texture button_text, board_button_text;
    TextureRegion button_roll_ok, button_roll_nope, button_board_ok, button_board_nope;
    TextureRegionDrawable myTexRegionDrawable, board_ok, board_nope;
    ImageButton button_roll;
    BitmapFont font, boardFont;
    FreeTypeFontGenerator generator;
    FreeTypeFontParameter parameter;
    int tabPoint[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1} ;
    
    ArrayList<Image> board_buttons;
    
    int nbLance = 2;
    
   
	@Override
	public void create () {
		
		Gdx.graphics.setTitle("Yams AP Version");
		
		// On g�nere les d�s
		dices = new ArrayList<Dice>();
		batch = new SpriteBatch();
		for(int i=0; i < 5; i++)
			dices.add(new Dice());
		
		
		//on g�nere la font
        generator = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        parameter.size = 15;
        boardFont = generator.generateFont(parameter); // font size 12 pixels
        parameter.size = 20;
		
		//On cr�er le boutton
		button_text = new Texture(Gdx.files.internal("button.png"));
		button_roll_ok = new TextureRegion(button_text,0,0,163,34);
		button_roll_nope = new TextureRegion(button_text,0,34,163,34);
	    myTexRegionDrawable = new TextureRegionDrawable(button_roll_ok);
	    button_roll = new ImageButton(myTexRegionDrawable); //Set the button up
	    button_roll.setPosition(150, 25);
	    
	    stage = new Stage(new ScreenViewport()); 
        stage.addActor(button_roll);
	    
	    // On g�n�re les boutons du board
	    board_buttons = new ArrayList<Image>();
	    board_button_text = new Texture(Gdx.files.internal("filter.png"));
	    button_board_ok =  new TextureRegion(board_button_text,0,17,32,17);
	    button_board_nope =  new TextureRegion(board_button_text,0,0,32,17);
	    board_ok = new TextureRegionDrawable(button_board_ok); 
	    board_nope = new TextureRegionDrawable(button_board_nope); 
	   
	    for(int i = 0; i < 6; i++)
	    {
	    	board_buttons.add(new Image(board_ok));
	    	board_buttons.get(i).setPosition(605, 491 - (19*i));
	    	stage.addActor(board_buttons.get(i));
	    }
	    
	    for(int i = 6; i < 13; i++)
	    {
	    	board_buttons.add(new Image(board_ok));
	    	board_buttons.get(i).setPosition(605, 445 - (19*i));
	    	stage.addActor(board_buttons.get(i));
	    }
	    
	    // sommes des 1
	    board_buttons.get(0).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(0).setDrawable(board_nope);
	    	board_buttons.get(0).removeListener(this);
	    	tabPoint[0] = sommeDices(1);
	    	resetRollButton();
	    };});
	    
	    //sommes des 2
	    board_buttons.get(1).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(1).setDrawable(board_nope);
	    	board_buttons.get(1).removeListener(this);
	    	tabPoint[1] = sommeDices(2) *2;
	    	resetRollButton();
	    };});
	    
	  //sommes des 3
	    board_buttons.get(2).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(2).setDrawable(board_nope);
	    	board_buttons.get(2).removeListener(this);
	    	tabPoint[2] = sommeDices(3) * 3;
	    	resetRollButton();
	    };});
	    
		//sommes des 4
	    board_buttons.get(3).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(3).setDrawable(board_nope);
	    	board_buttons.get(3).removeListener(this);
	    	tabPoint[3] = sommeDices(4) * 4;
	    	resetRollButton();
	    };});
	    
		//sommes des 5
	    board_buttons.get(4).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(4).setDrawable(board_nope);
	    	board_buttons.get(4).removeListener(this);
	    	tabPoint[4] = sommeDices(5) * 5;
	    	resetRollButton();
	    };});
	    
		//sommes des 6
	    board_buttons.get(5).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(5).setDrawable(board_nope);
	    	board_buttons.get(5).removeListener(this);
	    	tabPoint[5] = sommeDices(6) * 6;
	    	resetRollButton();
	    };});
	    
	    
	    //brelan
	    board_buttons.get(6).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(6).setDrawable(board_nope);
	    	board_buttons.get(6).removeListener(this);
	    	tabPoint[6] = brelan() * 3;
	    	resetRollButton();
	    };});
	    
	    //carre
	    board_buttons.get(7).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(7).setDrawable(board_nope);
	    	board_buttons.get(7).removeListener(this);
	    	tabPoint[7] = carre() * 4;
	    	resetRollButton();
	    };});
	    
	    //full house
	    board_buttons.get(8).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(8).setDrawable(board_nope);
	    	board_buttons.get(8).removeListener(this);
	    	tabPoint[8] = full();
	    	resetRollButton();
	    };});
	    
	    //smallSuite
	    board_buttons.get(9).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(9).setDrawable(board_nope);
	    	board_buttons.get(9).removeListener(this);
	    	tabPoint[9] = smallSuite();
	    	resetRollButton();
	    };});
	    
	    // bigSuite
	    board_buttons.get(10).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(10).setDrawable(board_nope);
	    	board_buttons.get(10).removeListener(this);
	    	tabPoint[10] = bigSuite();
	    	resetRollButton();
	    };});
	    
	    //yams
	    board_buttons.get(11).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(11).setDrawable(board_nope);
	    	board_buttons.get(11).removeListener(this);
	    	tabPoint[11] = yams();
	    	resetRollButton();
	    };});
	    
	    //chance
	    board_buttons.get(12).addListener( new ClickListener() { public void clicked(InputEvent event, float x, float y) {
	    	board_buttons.get(12).setDrawable(board_nope);
	    	board_buttons.get(12).removeListener(this);
	    	tabPoint[12] = luck();
	    	resetRollButton();
	    };});
	    
	    
        // On ajoute les d�s dans la sc�ne
        for(int i = 0; i < 5;i++)
        	stage.addActor(dices.get(i).getImage());
        
        Gdx.input.setInputProcessor(stage); 
        
        // event dice
        dices.get(0).getImage().addListener( new ClickListener() {    
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("D�s 0");
        		dices.get(0).keepDice();
        	};
        });
        
        dices.get(1).getImage().addListener( new ClickListener() {    
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("D�s 1");
        		dices.get(1).keepDice();
        	};
        });
        
        dices.get(2).getImage().addListener( new ClickListener() {    
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("D�s 2");
        		dices.get(2).keepDice();
        	};
        });
        
        dices.get(3).getImage().addListener( new ClickListener() {    
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("D�s 3");
        		dices.get(3).keepDice();
        	};
        });
        
        dices.get(4).getImage().addListener( new ClickListener() {    
        	@Override
        	public void clicked(InputEvent event, float x, float y) {
        		System.out.println("D�s 4");
        		dices.get(4).keepDice();
        	};
        });
        
        
        // On g�n�re le listener du bouton 
        button_roll.addListener( new ClickListener() {    
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	
            	boolean disable = false;
            	if(nbLance > 0)
            		nbLance--;
            	else 
            		disable = true;
            	
            		
            	if(nbLance < 1)
            	{
            		myTexRegionDrawable.setRegion(button_roll_nope);
            		button_roll.setBackground(myTexRegionDrawable);
            		parameter.color = Color.LIGHT_GRAY;
            		font = generator.generateFont(parameter); // font size 12 pixels
            	}
            	if(nbLance >= 0 && !disable)
            	{
            		
            		System.out.println("**********************************");
                	int combinaison = (int)(Math.random()*(Dice.dice_number_combinaison));
                	for(int i=0; i < 5; i++)
    	    			dices.get(i).roll(combinaison);
            	}
            };
        });
        	
		// On g�nere le background
		bg = new Texture("bg.png");
		board = new Texture("board.png");
		
		
       
	}

	@Override
	public void render () {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.draw(board, 0, 0);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
	    stage.draw(); 
	    
	    batch.begin();
	    font.draw(batch, "Lancer ["+ nbLance+"]", 190, 50);

	    if(tabPoint[0] != -1) { boardFont.draw(batch, "" +tabPoint[0], 608, 505); }
	    if(tabPoint[1] != -1) { boardFont.draw(batch, "" +tabPoint[1], 608, 485); }
	    if(tabPoint[2] != -1) { boardFont.draw(batch, "" +tabPoint[2], 608, 466); }
	    if(tabPoint[3] != -1) { boardFont.draw(batch, "" +tabPoint[3], 608, 448); }
	    if(tabPoint[4] != -1) { boardFont.draw(batch, "" +tabPoint[4], 608, 429); }
	    if(tabPoint[5] != -1) { boardFont.draw(batch, "" +tabPoint[5], 608, 411); }
	    
	    
	    //calcul total sup
	    int totalsup = 0;
	    if(tabPoint[0] > -1) {totalsup += tabPoint[0];}
	    if(tabPoint[1] > -1) {totalsup += tabPoint[1];}
	    if(tabPoint[2] > -1) {totalsup += tabPoint[2];}
	    if(tabPoint[3] > -1) {totalsup += tabPoint[3];}
	    if(tabPoint[4] > -1) {totalsup += tabPoint[4];}
	    if(tabPoint[5] > -1) {totalsup += tabPoint[5];}
	    
	    
	    int supbonus = 0;
	    if(totalsup > 62)
	    	supbonus = 35;
	    	
	    int totalfullsup = totalsup + supbonus;
	    boardFont.draw(batch, "" +totalfullsup, 608, 374);
	    boardFont.draw(batch, "" +supbonus, 608, 392);
	    
	    if(tabPoint[6] != -1) { boardFont.draw(batch, "" +tabPoint[6], 608, 345); } // brelan
	    if(tabPoint[7] != -1) { boardFont.draw(batch, "" +tabPoint[7], 608, 325); } // carre
	    if(tabPoint[8] != -1) { boardFont.draw(batch, "" +tabPoint[8], 608, 306); } // full
	    if(tabPoint[9] != -1) { boardFont.draw(batch, "" +tabPoint[9], 608, 288); } // smallSuite
	    if(tabPoint[10] != -1) { boardFont.draw(batch, "" +tabPoint[10], 608, 270); } // bigSuite
	    if(tabPoint[11] != -1) { boardFont.draw(batch, "" +tabPoint[11], 608, 250); } // yams
	    if(tabPoint[12] != -1) { boardFont.draw(batch, "" +tabPoint[12], 608, 232); } // luck
	    
	    
	    //calcul total sup
	    int totalinf = 0;
	    if(tabPoint[6] > -1) {totalinf += tabPoint[6];}
	    if(tabPoint[7] > -1) {totalinf += tabPoint[7];}
	    if(tabPoint[8] > -1) {totalinf += tabPoint[8];}
	    if(tabPoint[9] > -1) {totalinf += tabPoint[9];}
	    if(tabPoint[10] > -1) {totalinf += tabPoint[10];}
	    if(tabPoint[11] > -1) {totalinf += tabPoint[11];}
	    if(tabPoint[12] > -1) {totalinf += tabPoint[12];}
	    
	    boardFont.draw(batch, "" +totalinf, 608, 214);
	    int totaltotal = totalinf+ totalfullsup;
	    boardFont.draw(batch, ""+ totaltotal, 608, 185);
	    	
	    
		batch.end();
	    
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		for(int i=0; i < 5; i++)
			dices.get(i).dispose();
		bg.dispose();
		board.dispose();
		generator.dispose(); 
		
	}
	
	public void resetRollButton()
	{
		int combinaison = (int)(Math.random()*(Dice.dice_number_combinaison));
    	for(int i=0; i < 5; i++)
    	{
    		dices.get(i).resetKeep();
    		dices.get(i).roll(combinaison);
    	}
			
    	
		//reset du boutons
    	nbLance = 2;
    	parameter.color = Color.BLACK;
        font = generator.generateFont(parameter); // font size 12 pixels
        myTexRegionDrawable.setRegion(button_roll_ok);
	}
	
	/* renvoie le nombre de d�s poss�dant cette valeur */
	public int sommeDices( int somme)
	{
		int cpt = 0;
		for(int i=0; i < 5; i++)
		{
			if(dices.get(i).getValue() == somme-1)
				cpt++;
		}
		return cpt;	
	}
	
	public int brelan()
	{
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		for(int i=0; i < 6; i++)
		{
			if(tab[i] >= 3)
				return i+1;
		}
		
		return 0;
	}
	
	public int carre()
	{
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		for(int i=0; i < 6; i++)
		{
			if(tab[i] >= 4)
				return i+1;
		}
		
		return 0;
	}
	
	public int full()
	{
		int id = brelan() -1;
		int tab[] ={0,0,0,0,0,0};
		if(id >= 0)
		{
			for(int i=0; i < 5; i++)
			{
				tab[dices.get(i).getValue()]++;
			}
			
			tab[id] = 0;
		
			for(int i=0; i < 6; i++)
			{
				if(tab[i] == 2)
					return /*(i+1 *2) + (id+1)*3 +*/ 25;
			}	
		}
		
		return 0; 
	}
	
	public int smallSuite()
	{
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		if (tab[0] == 1 && tab[1] == 1 && tab[2] == 1 && tab[3] == 1 && tab[4] == 1)
			return 30;
		return 0;
	}
	
	public int bigSuite()
	{
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		if (tab[5] == 1 && tab[1] == 1 && tab[2] == 1 && tab[3] == 1 && tab[4] == 1)
			return 40;
		return 0;
	}
	
	public int yams()
	{
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		for(int i=0; i < 6; i++)
		{
			if(tab[i] == 5)
				return 50;
		}
		return 0;
	}
	
	public int luck()
	{
		int total = 0;
		int tab[] ={0,0,0,0,0,0};
		for(int i=0; i < 5; i++)
		{
			tab[dices.get(i).getValue()]++;
		}
		
		for(int i=0; i < 6; i++)
		{
			total+= (i+1)*tab[i];
		}
		return total;
	}
}


