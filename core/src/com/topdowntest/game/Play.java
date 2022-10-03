package com.topdowntest.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Play implements Screen {
	TiledMap tiledMap;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	Player player;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();

		tiledMapRenderer.setView(camera);

		tiledMapRenderer.getBatch().begin();
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(0));
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(1));
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(2));
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(3));
		player.draw(tiledMapRenderer.getBatch());
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(4));
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(5));
		tiledMapRenderer.renderTileLayer((TiledMapTileLayer) tiledMap.getLayers().get(6));
		tiledMapRenderer.getBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void show() {
		tiledMap = new TmxMapLoader().load("samplemap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		camera = new OrthographicCamera();

		player = new Player(new Sprite(new Texture("redhead-v2.png")), (TiledMapTileLayer) tiledMap.getLayers().get(0));
		player.setPosition(4 * player.getcollisionLayer().getTileWidth(),
				10 * player.getcollisionLayer().getTileHeight());

		Gdx.input.setInputProcessor(player);

	}

	@Override
	public void dispose() {
		tiledMapRenderer.dispose();
		tiledMap.dispose();
		player.getTexture().dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}