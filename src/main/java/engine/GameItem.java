package engine;

import org.joml.Vector3f;

import engine.rendering.Mesh;
import static engine.Utils.*;

public class GameItem {

	private final Mesh mesh;

	private final Vector3f position;

	private float scale;

	private final Vector3f rotation;

	public GameItem(Mesh mesh) {
		this.mesh = mesh;
		position = new Vector3f(0, 0, 0);
		scale = 1;
		rotation = new Vector3f(0, 0, 0);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(int x, int y, float z) {
		this.position.x = convertToFloatXCoord(x);
		this.position.y = convertToFloatYCoord(y);
		this.position.z = z;
	}

	public void setXPosition(int x) {
		this.position.x = convertToFloatXCoord(x);
	}

	public void setYPosition(int y) {
		this.position.y = convertToFloatYCoord(y);
	}

	public void setZPosition(float z) {
		this.position.z = z;
	}

	public float getXPosition() {
		return position.x;
	}

	public float getYPosition() {
		return position.y;
	}

	public float getZPosition() {
		return position.z;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(int x, int y, int z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}

	public Mesh getMesh() {
		return mesh;
	}
}