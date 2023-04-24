import pygame
import random

# Initialisation de Pygame
pygame.init()

# Couleurs
white = (255, 255, 255)
black = (0, 0, 0)
gray = (128, 128, 128)

# Nombre de nœuds et de liaisons
n = 30
m = 50

# Calcul de la taille des nœuds en fonction du nombre de nœuds
node_size = max(1, int(500 / n))

# Dimensions de la fenêtre
margin = 50
width = pygame.display.Info().current_w
height = pygame.display.Info().current_h

# Création de la fenêtre
screen = pygame.display.set_mode((width, height), pygame.FULLSCREEN)
pygame.display.set_caption("Recherche d'algorithme")

# Liste de nœuds et de liaisons
nodes = []
edges = []

# Création de nœuds aléatoires
for i in range(n):
    x = random.randint(margin + node_size, width - margin - node_size)
    y = random.randint(margin + node_size, height - margin - node_size)
    nodes.append((x, y))

# Création de liaisons aléatoires
for i in range(m):
    i, j = random.sample(range(n), 2)
    edges.append((i, j))

# Boucle principale
running = True
while running:
    # Traitement des événements
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Effacement de l'écran
    screen.fill(white)

    # Dessin des nœuds
    for i in range(n):
        pygame.draw.circle(screen, black, nodes[i], node_size)

    # Dessin des liaisons
    for edge in edges:
        i, j = edge
        x1, y1 = nodes[i]
        x2, y2 = nodes[j]
        pygame.draw.line(screen, gray, (x1, y1), (x2, y2), 2)

    # Mise à jour de l'affichage
    pygame.display.flip()

# Fermeture de Pygame
pygame.quit()
