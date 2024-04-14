from django.shortcuts import render

import socket
hostname = socket.gethostbyname(socket.gethostname())

# Create your views here.
def show_home(request):
    return render(request, 'index.html')

def login(request):
    return render(request, 'login.html')

def register(request):
    return render(request, 'login.html')

def addQuiz(request):
    return render(request, 'add_quiz.html')

def invite(request, id):
    return render(request, 'invite_quiz.html', {'id': id, 'host': hostname})

def play(request, id):
    return render(request, 'play.html', {'id': id})