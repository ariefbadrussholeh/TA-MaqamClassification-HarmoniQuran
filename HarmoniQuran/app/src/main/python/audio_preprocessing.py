import librosa
import numpy as np
from os.path import dirname, join

def load_audio(file_path):
    y, sr = librosa.load(file_path, sr=44100)
    y, _ = librosa.effects.trim(y=y)
    non_silent_intervals = librosa.effects.split(y, top_db=30)
    y = np.concatenate([y[start:end] for start, end in non_silent_intervals])

    if librosa.get_duration(y=y, sr=44100) > 30:
        y = y[:int(30 * 44100)]

    return y

def normalize(data, params_file):
    normalization_params = np.load(params_file)
    mean = normalization_params['mean']
    std = normalization_params['std']
    normalized_test_data = (data - mean) / std
    return normalized_test_data

def extract_features_37(y, sr=44100):
    mfccs = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=20)
    zcrs = librosa.feature.zero_crossing_rate(y)
    chroma = librosa.feature.chroma_cqt(y=y, sr=sr, bins_per_octave=24)
    rms = librosa.feature.rms(y=y)
    cent = librosa.feature.spectral_centroid(y=y, sr=sr)
    spec_bw = librosa.feature.spectral_bandwidth(y=y, sr=sr)
    rolloff = librosa.feature.spectral_rolloff(y=y, sr=sr, roll_percent=0.95)

    feature_vectors = np.concatenate((
        mfccs.T, zcrs.T, chroma.T, rms.T, cent.T, spec_bw.T, rolloff.T), axis=1)

    return feature_vectors

def preprocess_audio(file_path):
    y = load_audio(file_path)
    feature = extract_features_37(y)
    feature = np.mean(feature, axis=0)
    file_path = join(dirname(__file__), "saved/Deep ANN (Mean)_Norm.npz")
    feature_normalized = normalize(feature, file_path)
    return feature_normalized