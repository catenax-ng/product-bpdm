{{/*
Expand the name of the chart.
*/}}
{{- define "bpdm.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "bpdm.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "bpdm.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "bpdm.labels" -}}
helm.sh/chart: {{ include "bpdm.chart" . }}
{{ include "bpdm.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "bpdm.selectorLabels" -}}
app.kubernetes.io/name: {{ include "bpdm.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create name of application secret
*/}}
{{- define "bpdm.applicationSecret.name" -}}
{{- printf "%s-application" (include "bpdm.fullname" .) }}
{{- end }}

{{/*
Determine service name of PostgresDb
Infer name from deployed dependency or take name given by user if dependeny not enabled
*/}}
{{- define "bpdm.postgres.name" -}}
{{- if .Values.postgres.enabled }}
{{- include "includeWithPostgresContext" (list $ "postgresql.primary.fullname") }}
{{- else }}
{{- .Values.postgres.existingName }}
{{- end }}

{{/*
Determine service name of Elasticsearc h
Infer name from deployed dependency or take name given by user if dependeny not enabled
*/}}
{{- define "bpdm.elastic.name" -}}
{{- if .Values.elastic.enabled }}
{{- include  include "includeWithElasticContext" (list $ "elasticsearch.master.fullname") }}
{{- else }}
{{- .Values.elastic.existingName }}
{{- end }}


{{/*
Invoke include on given definition with postgresql dependency context
Usage: include "includeWithPostgresContext" (list $ "your_include_function_here")
*/}}
{{- define "includeWithPostgresContext" -}}
{{- $ := index . 0 }}
{{- $function := index . 1 }}
{{- include $function (dict "Values" $.Values.postgres "Chart" (dict "Name" "postgres") "Release" $.Release) }}
{{- end }}

{{/*
Invoke include on given definition with elastic dependency context
Usage: include "includeWithElasticContext" (list root "your_include_function_here")
*/}}
{{- define "includeWithElasticContext" -}}
{{- $ := index . 0 }}
{{- $function := index . 1 }}
{{- include $function (dict "Values" $.Values.elastic "Chart" (dict "Name" "elastic") "Release" $.Release) }}
{{- end }}
