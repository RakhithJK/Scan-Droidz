package net.scandroidz.states;

import net.scandroidz.core.State;
import net.scandroidz.core.StateManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainMenu implements State {
	private Rectangle hunt;
	private Rectangle bestiary;
	private Rectangle profile;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	private Texture huntButton;
	private Texture bestiaryButton;
	private Texture	profileButton;

	private boolean switchingState = false;
	
	@Override
	public void startUp() {
		hunt = new Rectangle(Gdx.graphics.getWidth()/2, 
				Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/3, 
				Gdx.graphics.getWidth()/2, 
				Gdx.graphics.getHeight()/3);
		
		bestiary = new Rectangle(Gdx.graphics.getWidth()/2,
				Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/3)*2),
				Gdx.graphics.getWidth()/2,
				Gdx.graphics.getHeight()/3);
		
		profile = new Rectangle(Gdx.graphics.getWidth()/2,
				Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/3)*3),
				Gdx.graphics.getWidth()/2,
				Gdx.graphics.getHeight()/3);
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/font16.fnt"),
		         Gdx.files.internal("data/font16.png"), false);
		
		huntButton = new Texture(Gdx.files.internal("data/playButton.png"));
		bestiaryButton = new Texture(Gdx.files.internal("data/optionsButton.png"));
		profileButton = new Texture(Gdx.files.internal("data/exitButton.png"));
		
		Gdx.app.log("MainMenu", "Startup");
	}

	@Override
	public void render() {
		font.setColor(0.0f, 0.0f, 1.0f, 1.0f); // tint font blue
		spriteBatch.begin();
		
		font.draw(spriteBatch, "MainMenu", 0, Gdx.graphics.getHeight());
		
		//Draw buttons
		spriteBatch.draw(huntButton, hunt.x, hunt.y, hunt.width, hunt.height);
		spriteBatch.draw(bestiaryButton, bestiary.x, bestiary.y, bestiary.width, bestiary.height);
		spriteBatch.draw(profileButton, profile.x, profile.y, profile.width, profile.height);

		spriteBatch.end();
	}

	@Override
	public void update(float delta) {
		if(Gdx.input.justTouched()) {
			System.out.println("Here!!");
			Vector2 currentTouch = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			if(hunt.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("Hunt", "Pressed");
				StateManager.getInstance().renderPaused = true;
				StateManager.getInstance().changeState("Scanning");
			}
			else if(bestiary.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("Bestiary", "Pressed");
			}
			else if(profile.contains(currentTouch.x, currentTouch.y)) {
				Gdx.app.log("Profile", "Pressed");
			}
		}
	}

	@Override
	public void load() {
	}

	@Override
	public void cleanUp() {
		spriteBatch = null;
		hunt = null;
		bestiary = null;
		profile = null;
		huntButton = null;
		bestiaryButton = null;
		profileButton = null;
	}

}
