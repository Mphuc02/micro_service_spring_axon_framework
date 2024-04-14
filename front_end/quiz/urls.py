from django.contrib import admin
from . import views
from django.urls import path

urlpatterns = [
    path('', views.show_home , name='home'), 
    path('login', views.login, name='login'),
    path('register', views.register, name='register'),
    path('add-quiz', views.addQuiz, name='addQuiz'),
    path('invite/<str:id>', views.invite, name='invite'),
    path('play/<str:id>', views.play, name='play'),
] 