import tensorflow as tf
from tensorflow import keras

def cnn_1d(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=X_train.shape[1:]),
    keras.layers.Conv1D(32, kernel_size=3, activation='relu'),
    keras.layers.MaxPooling1D(pool_size=3, padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Conv1D(32, kernel_size=3, activation='relu'),
    keras.layers.MaxPooling1D(pool_size=3, padding='valid'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(512, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(265, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def cnn(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2], 1)),
    keras.layers.Conv2D(32, kernel_size=(3, 3), activation='relu'),
    keras.layers.MaxPooling2D(pool_size=(3, 3), padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Conv2D(32, kernel_size=(3, 3), activation='relu'),
    keras.layers.MaxPooling2D(pool_size=(3, 3), padding='valid'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(512, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(265, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def lstm(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2])),
    keras.layers.LSTM(1024, return_sequences=True),
    keras.layers.Dropout(0.3),
    keras.layers.BatchNormalization(),
    keras.layers.LSTM(512, return_sequences=True),
    keras.layers.Dropout(0.3),
    keras.layers.BatchNormalization(),
    keras.layers.LSTM(216),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(512, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(265, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def lstm_1d(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=X_train.shape[1:]),
    keras.layers.LSTM(1024, return_sequences=True),
    keras.layers.Dropout(0.3),
    keras.layers.BatchNormalization(),
    keras.layers.LSTM(512, return_sequences=True),
    keras.layers.Dropout(0.3),
    keras.layers.BatchNormalization(),
    keras.layers.LSTM(216),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(512, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(265, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def cnn_lstm(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2], 1)),
    keras.layers.Conv2D(64, kernel_size=(3, 3), activation='relu'),
    keras.layers.MaxPooling2D(pool_size=(3, 3), padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Conv2D(32, kernel_size=(3, 3), activation='relu'),
    keras.layers.MaxPooling2D(pool_size=(3, 3), padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.TimeDistributed(keras.layers.Flatten()),
    keras.layers.LSTM(512),
    keras.layers.Dropout(0.25),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def cnn_lstm_1d(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=X_train.shape[1:]),
    keras.layers.Conv1D(64, kernel_size=3, activation='relu'),
    keras.layers.MaxPooling1D(pool_size=3, padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Conv1D(32, kernel_size=3, activation='relu'),
    keras.layers.MaxPooling1D(pool_size=3, padding='same'),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.TimeDistributed(keras.layers.Flatten()),
    keras.layers.LSTM(512),
    keras.layers.Dropout(0.25),
    keras.layers.BatchNormalization(),
    keras.layers.Flatten(),
    keras.layers.Dense(100, activation='relu'),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def deep_ann(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2])),
    keras.layers.Flatten(),
    keras.layers.Dense(1024, activation="relu"),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(512, activation="relu"),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(256, activation="relu"),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(128, activation="relu"),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

def deep_ann_1d(X_train):
  model = keras.models.Sequential([
    keras.layers.Input(shape=(X_train.shape[1],)),
    keras.layers.Dense(1024, activation="relu"),
    keras.layers.Dropout(0.2),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(512, activation="relu"),
    keras.layers.Dropout(0.1),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(256, activation="relu"),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(128, activation="relu"),
    keras.layers.BatchNormalization(),
    keras.layers.Dense(7, activation="softmax")
  ])
  return model

# def cnn(X_train):
#   model = keras.models.Sequential([
#     keras.layers.Input(shape=(X_train.shape[1], X_train.shape[2], 1)),
#     keras.layers.Conv2D(64, kernel_size=(3, 3), activation='relu', kernel_regularizer=tf.keras.regularizers.l2(0.001)),
#     keras.layers.BatchNormalization(),
#     keras.layers.MaxPooling2D(pool_size=(3, 3), strides=(2,2), padding='same'),
#     keras.layers.Conv2D(32, kernel_size=(3, 3), activation='relu', kernel_regularizer=tf.keras.regularizers.l2(0.001)),
#     keras.layers.BatchNormalization(),
#     keras.layers.MaxPooling2D(pool_size=(3, 3), strides=(2,2), padding='same'),    
#     keras.layers.Conv2D(32, kernel_size=(2, 2), activation='relu', kernel_regularizer=tf.keras.regularizers.l2(0.001)),
#     keras.layers.BatchNormalization(),
#     keras.layers.MaxPooling2D(pool_size=(2, 2), strides=(2,2), padding='same'),
#     keras.layers.Flatten(),
#     keras.layers.Dense(256, activation='relu'),
#     keras.layers.Dropout(0.5),
#     keras.layers.BatchNormalization(),
#     keras.layers.Dense(128, activation='relu'),
#     keras.layers.Dropout(0.5),
#     keras.layers.BatchNormalization(),
#     keras.layers.Dense(7, activation="softmax")
#   ])
#   return model