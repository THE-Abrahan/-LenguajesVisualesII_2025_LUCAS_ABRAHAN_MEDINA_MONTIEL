import tkinter as tk
from tkinter import messagebox
import webbrowser
import subprocess

# --- CONFIGURACIÓN ---
# Ruta al ejecutable del navegador Brave.
# ¡IMPORTANTE! Revisa si esta ruta es correcta en tu PC.
# Puedes encontrarla haciendo clic derecho en el ícono de Brave -> Propiedades -> Destino.
BRAVE_PATH = r"C:\Program Files\BraveSoftware\Brave-Browser\Application\brave.exe"
URL_TO_OPEN = "http://localhost:8080/"

def attempt_login():
    """
    Valida las entradas y abre el navegador si no están vacías.
    """
    username = user_entry.get()
    password = pass_entry.get()

    if not username or not password:
        messagebox.showwarning("Campos incompletos", "Por favor, ingresa tu usuario y contraseña.")
        return

    print(f"Usuario '{username}' ha iniciado sesión. Abriendo la aplicación web...")

    try:
        # Opción 1: Usando webbrowser (más compatible)
        # Registra Brave como un navegador disponible para webbrowser.
        webbrowser.register('brave', None, webbrowser.BackgroundBrowser(BRAVE_PATH))
        webbrowser.get('brave').open_new(URL_TO_OPEN)

        # Opción 2: Usando subprocess (más directo)
        # subprocess.Popen([BRAVE_PATH, URL_TO_OPEN])

        # Cierra la ventana de login después de abrir el navegador
        root.destroy()

    except FileNotFoundError:
        messagebox.showerror("Error", f"No se encontró el navegador Brave en la ruta:\n{BRAVE_PATH}\n\nPor favor, corrige la ruta en el archivo launcher.py.")
    except Exception as e:
        messagebox.showerror("Error inesperado", f"Ocurrió un error al intentar abrir el navegador: {e}")

# --- Creación de la ventana de Login ---
root = tk.Tk()
root.title("Login - Lanzador de Aplicación")
root.geometry("300x150")
root.resizable(False, False)

frame = tk.Frame(root, padx=10, pady=10)
frame.pack(expand=True)

tk.Label(frame, text="Usuario:").grid(row=0, column=0, sticky="w", pady=2)
user_entry = tk.Entry(frame)
user_entry.grid(row=0, column=1, pady=2)

tk.Label(frame, text="Contraseña:").grid(row=1, column=0, sticky="w", pady=2)
pass_entry = tk.Entry(frame, show="*")
pass_entry.grid(row=1, column=1, pady=2)

login_button = tk.Button(frame, text="Iniciar Sesión", command=attempt_login)
login_button.grid(row=2, columnspan=2, pady=10)

root.mainloop()