map = null
pressed = null

non.ready = ->
  map = non.tiled.newMap("map.tmx")
  non.audio.play non.audio.newMusic("music.ogg")
  return

non.update = ->
  if non.keyboard.isKeyPressed("Space")
    pressed = "Key pressed: Spacebar (release Spacebar to test)"
  else
    pressed = "Key pressed: None (press Spacebar to test)"
  return

non.draw = ->
  non.graphics.draw map
  non.graphics.draw "Author: YourBestNightmare", 10, 10, non.graphics.newColor("yellow")
  non.graphics.draw "Engine: non (no nonsense) framework", 10, 34
  non.graphics.draw "Description: In this example we are testing music, input, tmx rendering, images and text displaying.", 10, 58
  non.graphics.draw pressed, 10, 82, non.graphics.newColor("cyan")
  non.graphics.draw "FPS: " + non.getFPS(), 10, 104
  return