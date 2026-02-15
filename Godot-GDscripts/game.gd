extends Node2D

@onready var musica = $AudioStreamPlayer2D2

func _ready():
	musica.play()
