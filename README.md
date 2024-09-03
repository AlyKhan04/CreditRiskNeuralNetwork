## Overview
This project is a Neural Network built from scratch in Java to evaluate a Credit Risk dataset (https://www.kaggle.com/datasets/laotse/credit-risk-dataset).<br>
The Neural Network was built without using any external libraries. The NN uses the sigmoid activation function: f(x) = (1/(1+e^-x).<br>
K-Cross validation with 5 folds was used in the training process for a better estimate of the models performance and 25 epochs are used per fold. <br>
The output from the output neuron is converted into Binary Classification (0 means loan is rejected, 1 means loan is accepted). <br>
To test the model, one can test any combination of epochs, learning rate, hidden layer neurons and folds. I found the optimal combination to be 25 epochs, a learning rate of 0.05, 15 hidden layer neurons and 5 folds. This keeps the model from overfitting while mainaining high precision, accuracy and true positive predictions.<br>

## Output
The output of the model prints out the following: 
1. The loss and accuracy of each epoch in the following format: Epoch Number - Loss: Double, Accuracy: Double <br>
2. The accuracy of predictions in the fold: Accuracy: <em> Double </em> <br>
3. The precision of the model in the fold: Precision: <em> Double </em> <br>
4. The recall of the model in the fold: Recall: <em> Double </em> <br>
5. The F1 score of the model in the fold: F1 Score: <em> Double </em> <br>
6. The number of True Positives, True Negatives, False Positives, False Negatives in the fold:<br>
True Positives (TP):<em> Number </em> <br>
True Negatives (TN):<em> Number </em> <br>
False Positives (FP):<em> Number </em> <br>
False Negatives (FN):<em> Number </em> <br>
7. A graph plotting the accuracy vs loss per epoch in each fold: <br>
/Users/alykhan/Desktop/Screenshot 2024-09-03 at 5.28.46 PM.png
All of these factors allow for indepth analysis of the models performance any given parameter choice. <br>

## How to download
1. Download the dataset from the following link: https://www.kaggle.com/datasets/laotse/credit-risk-dataset <br>
2. Clean and PreProcess the dataset by downloading and running the.ipynb file called <em> CreditDefaultModel.ipynb </em> <br>
3. Download the directory titled NN and open it in any IDE.<br>
4. Change the variable filename to the path of the standardized_data.csv that CreditDefaultModel.ipynb created. <br>
5. Run the project and enjoy.

## Findings
The model is very good at predicting negative values (where the loan is rejected) due to the abundance of datapoints in the dataset. However, due to the class imbalance in the set (80% loan rejected to 20% of loans accepted), the model is worse at predicting positive instances, even when random oversampling is implemented. <br>
The model has high accuracy and precision (ranging from 70-90%) which is good as it predicts most values accurately. <br>

