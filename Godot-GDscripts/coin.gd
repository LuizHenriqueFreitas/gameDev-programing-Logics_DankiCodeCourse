extends Area2D

@onready var game_manager = %GameManager
@onready var animated_sprite = $AnimatedSprite2D
@onready var audio = $AudioStreamPlayer2D
@onready var hitbox = $CollisionShape2D

func _ready():
	animated_sprite.play("default");

func _on_body_entered(body):
	audio.play()
	hitbox.disabled = true
	game_manager.add_point();
	animated_sprite.visible = false

func _on_audio_stream_player_2d_finished():
	queue_free()
