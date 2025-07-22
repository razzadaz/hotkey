package razz.hotkey;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class HotkeyV1AlphaClient implements ClientModInitializer {
	private static KeyBinding scrollLeft;
	private static KeyBinding scrollRight;

	@Override
	public void onInitializeClient() {
		// Register the keybinding
		scrollLeft = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.hotkey.left",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_J,
				"category.hotkey"
		));
		scrollRight = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.hotkey.right",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_K,
				"category.hotkey"
		));

		// Handle key press
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (scrollLeft.wasPressed()) {
				if (client.player != null) {
					client.player.getInventory().setSelectedSlot((client.player.getInventory().getSelectedSlot() + 8) % 9);
				}

			}
			while (scrollRight.wasPressed()) {
				if (client.player != null) {
					client.player.getInventory().setSelectedSlot((client.player.getInventory().getSelectedSlot() + 1) % 9);
				}
			}
		});
	}
}
