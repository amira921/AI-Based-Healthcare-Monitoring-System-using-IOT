<h1 align="center"><b>AI-Baseed Healthcare Monitoring System using IOT</b></h1>

The Proposed Healthcare Monitoring System using IoT and NLP aims to create an integrated platform that includes a smart band, mobile application, and generative question-answering system to facilitate efficient healthcare monitoring and medical assistance for patients and doctors. **The smart band** collects vital signs and stores them in a database for real-time access by both patients and healthcare providers. **BioGPT-PubMedQA-Prefix-Tuning Model**, implemented as a chatbot, assists patients with medical inquiries and provides initial prescriptions. Additionally, the chatbot serves as a doctor's assistant, assisting physicians with medical questions during patient consultations. **The mobile application** serves as the primary interface for users, both patients and doctors. It includes separate portals for patients and doctors, offering distinct features tailored to their needs <br>

<p align="center"><b>Check our Demo, Presentation, and Documentation</b></p>

<table align="center">
        <thead>
            <tr>
                <th><a href="https://drive.google.com/file/d/1GRfaLCeAsJMBQkwHePhZAvHr6Ljq1OeW/view?usp=drive_link">Demo</a></th>
                <th><a href="https://docs.google.com/presentation/d/18zAe3_MNPJdM6uUCGvMFbxXZhz2Odt_T/edit?usp=drive_link&ouid=114624475064812527364&rtpof=true&sd=true">Presentation</a></th>
	              <th><a href="https://drive.google.com/file/d/1roDkTniSn4huLCV1eD-7JIc8oi8I7jun/view?usp=sharing">Documentation</a></th>
            </tr>
        </thead>
        <tbody>
          <tr></tr>
          <tr></tr>
        </tbody>
    </table>

<br><br>

<h2>Graduation Project Score: [99:100] - [A+]</h2>
<h2>Project Contribution in Competitions</h2>

- **Dell Technologies' Envision the Future Competition**: emerged as semifinalists out of 283 global project teams in the Dell Technologies' Envision the Future Competition held in 2023
- **Egypt IoT & AI Challenge** : Participated with graduation project, sponsored with resources, financial support, and technical assistance.

<h1 align="center"><b>Project Overview</b></h1>

<h1><b>1. Mobile Application</b></h1>

>The mobile application serves as the primary interface for users, both patients, and doctors. It includes separate portals for patients and doctors, offering distinct features tailored to their needs.

<h2><b>Patient Portal - Features</b></h2>

- **Schedule Meeting** \
  Patients can schedule appointments and meetings with doctors based on their availability
- **Meetings with Doctors** \
  Patients can access and join virtual meetings with doctors, enabling remote consultations
- **Daily Symptoms Tracking** \
  Patients can log their daily symptoms and provide updates on their health condition. This information, along with the vital signs collected by the smart band, is stored in the system for future reference.
- **Medical History** \
  The patient portal maintains a comprehensive medical history for each patient, including previous diagnoses, treatments, and prescribed medications. It also incorporates the patient's vital signs collected by the smart band, facilitating a holistic view of the patient's health
- **Medical Chatbot** \
  The deployed Generative question-answering which provides initial prescriptions for common ailments

<h2><b>Doctor Portal - Features</b></h2>

- **Schedule Meeting**\
  Doctors can manage their schedules and availability for appointments and meetings with patients 
- **Meetings with Patients** \
   Doctors can conduct virtual meetings with patients, reviewing their medical history and vital signs collected by the smart band. This allows doctors to make informed decisions during consultations
- **Alert** \
  The system alerts doctors in case the vitals and symptoms of a patient are very serious and require immediate intervention, ensuring that urgent attention is provided to the patient
- **Medical Chatbot** \
  Similar to the patient portal, the doctor portal includes the medical chatbot to assist doctors in answering medical questions. The chatbot serves as an intelligent assistant, providing relevant information and suggestions based on the doctor's queries

## Finetuned BioGPT Model and Mobile App Deployment

API and Tokens used in App

```
API_URL = "https://api-inference.huggingface.co/models/Amira2045/BioGPT-Finetuned"
headers = {"Authorization": "Bearer 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"}
```
## Smart Band and Mobile App Connection

## **Technologies Used**
- IDE: Android Studio
- Java
- MySQL
- PHP
- XML
- Gson
- AppCompat
- constraint-layout
- Design
- Navigation
- JUnit
- Runner
- Espresso-Core
- LiveData
- ViewModel
- OkHttp  
- Volley
    
<h1><b>2. Chatbot - BioGPT-PubMedQA-Prefix-Tuning</b></h1>

>BioGPT-PubMedQA-Prefix-Tuning model used for fine-tuning is BioGPT, a Large Language Model (LLM) used in the medical domain, the goal of the fine-tuned model is to answer medical questions. The model is deployed as a chatbot within the mobile application, allowing users to ask health-related queries and receive accurate responses. The chatbot acts as a virtual medical assistant, providing initial prescriptions and guiding users based on their symptoms and medical history.

## Experimental Setup
- **Dataset:** PubMedQA
- **Model Architecture:** GPT-2 XL (Generative Pre-trained Transformer)
- **Pretrained Large Language Model (LLM):** BioGPT-Large
- **Fine-Tuning:** Soft prompt in prefix Tuning
- **Baselines:** Pretrained BioGPT and BioGPT large models without fine-tuning.
- **Evaluation Metrics: Baslines Comparison**
  <table>
        <thead>
            <tr>
              <th>   </th>
              <th>BioGPT-Large</th>
	            <th>BioGPT-PubMedQA-Prefix-Tuning</th>
            </tr>
        </thead>
        <tbody>
            <tr>
             <td>Loss</td> 
             <td>12.37</td>
             <td>9.20</td>
            </tr>
            <tr> 
             <td>Perplexity</td> 
             <td>237016.3</td>
             <td>1350.9</td>
            </tr>
        </tbody>
  </table>

    
## Dataset - PubMedQA
> PubMedQA _ Closed-domain question answering given PubMed abstract: the dataset contains questions on biomedical research that cover a wide range of biomedical topics, including diseases, treatments, genes, proteins, and more. PubMedQA is one of the MultiMedQA datasets (A benchmark for medical question answering). PubMedQA consists of 1k expert labeled, 61.2k unlabeled, and 211.3k artificially generated QA instances with yes/no/maybe multiple-choice answers and long answers given a question together with a PubMed abstract as context.                                 

<p align="center"><img src="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/assest/dataset%20usage.png" width="300"></p>

## Pretrained Model - BioGPT
   
> BioGPT, which was announced by Microsoft, can be used to analyze biomedical research with the aim of answering biomedical questions and can be especially relevant in helping researchers gain new insights. <br><br>
BioGPT is a type of generative language model, which is trained on millions of biomedical research articles that have already been published. This essentially means that BioGPT can use this information to perform other tasks like answering questions, extracting relevant data, and generating text relevant to biomedical. <br><br>
The researchers used GPT-2 XL as the primary model and trained it on 15 million PubMed abstracts before using it in the real world. GPT-2 XL is a Transformer decoder that has 48 layers, 1600 hidden sizes and 25 attention heads resulting in 1.5B parameters in total.

<p align="center"><img src="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/assest/model%20architecture.png" width="400px" heigth="300px"></p>

## Prefix Tuning with Peft and LoRA
> Fine-Tuning Setup: we performed soft prompt in prefix tuning technique on the BioGPT large **1.5B** model. The virtual tokens length was set to **10**, allowing us to focus on a specific context within the input sequence. By freezing the remaining parts of the model, we limited the number of trainable parameters to **1.5 million**. During the training process, we utilized a **TPU VM v3-8** with a batch size of **8** and num_warmup_steps = **1000** and gradient_accumulation_steps = **4** and weight_decay= **0.1**,this enabled us to execute the training procedure over **24** steps, with each step involving the processing of **1024** tokens. The Adam optimizer was employed, utilizing a peak learning rate of **1×10−5** to optimize the model's performance over the course of **3** epochs.

<p align="center"><img src="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/assest/finetuned%20model.png" width="400px" heigth="500px"></p>

## Model Deployment 

Finetuned BioGPT model is hosted on **Hugging Face**, we used the following API to deploy the model on our mobile app.

```
API_URL = "https://api-inference.huggingface.co/models/Amira2045/BioGPT-Finetuned"
headers = {"Authorization": "Bearer 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"}
```

## **Technologies Used**
- Python
- PyTorch
- Torch_XLA
- Sacremoses
- Transformers
- PEFT
- LoRA
- Datasets
- OS
- Random
- TQDM
- IDE: Kaggle Notebook
- HuggingFace Hub

<p align="center"> <a href ="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/ChatBot-Generative%20QA%20System/Proposed%20Paper.pdf"> Check our Paper for more Details</a></p>

<h1><b>3. Smart Band</b></h1>
The smart band is a wireless device equipped with sensors to measure vital signs such as temperature, oxygen levels, blood pressure, and heart rate. These measurements are transmitted to a central database for further processing and analysis. The smart band plays a critical role in collecting real-time health data, which is essential for accurate monitoring and diagnosis.

<p align="center"> <img src="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/assest/smart%20band%20prototype.jpg" heigth="300px" width="800px"> </p>

## **Technologies Used**
### Hardware
- Max30100 sensor
- LM35 sensor
- BMP180 sensor
- TFT ST7789V screen
- Arduino Nano
- Wi-Fi module ESP8266
  
### Software
- Arduino IDE
- Proteus
- Python
- C
- TouchScreen
- LCDWIKI_GUI
- LCDWIKI_KBV
- ESP8266WiFi
- MySQL_Connection
- MySQL_Cursor

## Connection with Mobile App
The MicroController(Arduino Nano) sends vital signs to our database using the Wi-Fi module ESP8266, then the mobile application fetches the data from the database.

<p align="center"><img src="https://github.com/amira921/AI-based-Healthcare-Monitoring-System-using-IoT/blob/main/assest/smart%20band%20architecture.png" width="500px" heigth="400px"> </p>
