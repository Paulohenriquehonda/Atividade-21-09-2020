package strategy.camera;

public class Camera {
	private SoftwareCamera SoftwareCamera;

	public Camera(SoftwareCamera softwareCamera) {
		this.SoftwareCamera = softwareCamera;
	}

	public void fotografar() {
		dispararFlash();
		softwareCamera.salvar();
	}

	private void dispararFlash() {
		System.out.println("Flash...");
	}

}
